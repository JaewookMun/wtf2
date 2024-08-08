package com.wtf2.erp.board.service;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardDetailsResponseDto;
import com.wtf2.erp.board.dto.BoardRequestDto;
import com.wtf2.erp.board.dto.BoardResponseDto;
import com.wtf2.erp.board.repository.BoardQuerydslRepository;
import com.wtf2.erp.board.repository.BoardRepository;
import com.wtf2.erp.common.dto.DataTableRequest;
import com.wtf2.erp.company.repository.CompanyRepository;
import com.wtf2.erp.config.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQuerydslRepository boardQuerydslRepository;
    private final CompanyRepository companyRepository;

    private static final String NON_TITLE = "제목없음";

    /**
     * 하위 페이지 리스트 조회
     * @param boardType Page
     * @param parentId
     * @return
     */
    public List<BoardResponseDto> getSubPageList(BoardType boardType, Long parentId) {

        return boardQuerydslRepository.findSubPages(boardType, parentId, getCurrentAuthenticatedUser().getCompany())
                .stream()
                .map(board -> new BoardResponseDto(board, board.getChildren()))
                .collect(Collectors.toList());
    }

    public List<BoardResponseDto> list(BoardType boardType, DataTableRequest dataTableRequest) {

        PageRequest pageRequest = PageRequest.of(dataTableRequest.getDraw()-1,
                dataTableRequest.getLength(),
                Sort.by(Sort.Direction.DESC, "createdDate"));

        return boardRepository.findByType(boardType, pageRequest).stream()
                .map(board -> new BoardResponseDto(board, board.getChildren()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long post(BoardRequestDto requestDto) {

        Board board = Board.builder()
                .title(requestDto.getTitle())
                .type(BoardType.valueOf(requestDto.getType()))
                .company(getCurrentAuthenticatedUser().getCompany())
                .build();

        board.addContent(requestDto.getContent());

        return boardRepository.save(board).getId();
    }

    public Long totalCount(BoardType boardType) {
        return boardRepository.countByType(boardType);
    }

    public BoardDetailsResponseDto getBoardDetails(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        return new BoardDetailsResponseDto(board.getTitle(), board.getContent().getText());
    }

    /**
     * post a blank page for parent page
     * @param parentId
     * @return
     */
    @Transactional
    public Long postSubPageFor(Long parentId) {
        Board parent = boardRepository.findById(parentId).orElseThrow(() -> new IllegalArgumentException());

        Board subBoard = new Board(NON_TITLE, BoardType.PAGE, getCurrentAuthenticatedUser().getCompany());
        subBoard.addContent("");

        parent.addChild(subBoard);
        // flush()를 사용하지 않으면 null 반환
        boardRepository.flush();

        return subBoard.getId();
    }

    private AppUserDetails getCurrentAuthenticatedUser() {
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser instanceof AppUserDetails) return (AppUserDetails) currentUser;

        throw new IllegalStateException("Not Authenticated");
    }

    @Transactional
    public void deletePage(Long id) {
        Board deleteTarget = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EMPTY"));

        if (!deleteTarget.getCompany().equals(getCurrentAuthenticatedUser().getCompany()))
            throw new IllegalStateException("DO NOT HAVE AUTH");

        if (!deleteTarget.getType().equals(BoardType.PAGE))
            throw new IllegalArgumentException("NOT SAME TYPE");

        boardRepository.delete(deleteTarget);
    }
}
