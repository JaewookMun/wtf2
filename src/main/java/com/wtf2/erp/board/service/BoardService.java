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

    public List<BoardResponseDto> getPageList(BoardType boardType, Long parentId) {

        return boardQuerydslRepository.findPagesByParent(boardType, parentId, getCurrentAuthenticatedUser().getCompany())
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

    private AppUserDetails getCurrentAuthenticatedUser() {
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser instanceof AppUserDetails) return (AppUserDetails) currentUser;

        throw new IllegalStateException("Not Authenticated");
    }

    public Long totalCount(BoardType boardType) {

        return boardRepository.countByType(boardType);
    }

    public BoardDetailsResponseDto getBoardDetails(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        return new BoardDetailsResponseDto(board.getTitle(), board.getContent().getText());
    }

}
