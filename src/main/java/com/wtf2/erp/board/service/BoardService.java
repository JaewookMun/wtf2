package com.wtf2.erp.board.service;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.domain.PageContent;
import com.wtf2.erp.board.dto.*;
import com.wtf2.erp.board.repository.BoardQuerydslRepository;
import com.wtf2.erp.board.repository.BoardRepository;
import com.wtf2.erp.board.repository.PageContentRepository;
import com.wtf2.erp.common.dto.DataTableRequest;
import com.wtf2.erp.config.security.form.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQuerydslRepository boardQuerydslRepository;
    private final PageContentRepository pageContentRepository;

    private static final String NON_TITLE = "제목없음";
    private static final String NULL_STRING = "";

    /**
     * 하위 페이지 리스트 조회
     * @param boardType Page
     * @param parentId
     * @return
     */
    public List<BoardResponseDto> getSubPageList(BoardType boardType, Long parentId) {

        return boardQuerydslRepository.findSubPages(boardType, parentId, getCurrentAuthenticatedUser().getGroup())
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
                .group(getCurrentAuthenticatedUser().getGroup())
                .build();

        board.addText(requestDto.getContent());

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
    public Long postPageFor(Long parentId) {

        Optional<Board> parent =
                Optional.ofNullable(parentId)
                        .filter(id -> id > -1)
                        .map(
                                id -> boardRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("NOT EXIST"))
                        );

        Board blankBoard = new Board(NON_TITLE, BoardType.PAGE, getCurrentAuthenticatedUser().getGroup());
        blankBoard.addText(NULL_STRING);

        parent.ifPresent(p -> p.addChild(blankBoard));

        boardRepository.save(blankBoard);

        return blankBoard.getId();
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

        Assert.state(deleteTarget.getGroup().equals(getCurrentAuthenticatedUser().getGroup()),
                "DO NOT HAVE AUTH");

        if (!deleteTarget.getType().equals(BoardType.PAGE))
            throw new IllegalArgumentException("NOT SAME TYPE");

        pageContentRepository.deleteByBoardId(id);
        boardRepository.delete(deleteTarget);
    }

    @Transactional
    public Long newPageLine(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("EMPTY"));

        Assert.state(board.getType().equals(BoardType.PAGE),
                "BOARD TYPE should be PAGE for new page line");

        board.addText(NULL_STRING);
        boardRepository.flush();

        return board.getLastPageLineId();
    }

    public PageDetailsDto getPageDetails(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow();

        List<TextLine> textLines = pageContentRepository.findByBoardIdOrderByIndex(id)
                .stream().map(TextLine::new)
                .collect(Collectors.toList());

        return PageDetailsDto.builder()
                .title(board.getTitle())
                .lines(textLines)
                .build();
    }

    @Transactional
    public Boolean updatePageLine(PageLineUpdateDto request) {
        PageContent pageContent = pageContentRepository.findByBoardIdAndId(request.getBoardId(), request.getLineId());
        pageContent.changeLine(request.getLineContent());

        return true;
    }
}
