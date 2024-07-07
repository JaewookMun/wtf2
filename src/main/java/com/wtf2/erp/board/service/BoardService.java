package com.wtf2.erp.board.service;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardRequestDto;
import com.wtf2.erp.board.dto.BoardResponseDto;
import com.wtf2.erp.board.repository.BoardRepository;
import com.wtf2.erp.common.dto.DataTableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDto> list(BoardType boardType, DataTableRequest dataTableRequest) {

        PageRequest pageRequest = PageRequest.of(dataTableRequest.getDraw()-1,
                dataTableRequest.getLength(),
                Sort.by(Sort.Direction.DESC, "createdDate"));

        return boardRepository.findByType(boardType, pageRequest).stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }


    public Long post(BoardRequestDto requestDto) {

        Board board = Board.builder()
                .title(requestDto.getTitle())
                .type(BoardType.valueOf(requestDto.getType()))
                .build();

        board.addContents(requestDto.getContent());

        return boardRepository.save(board).getId();
    }

    public Long totalCount(BoardType boardType) {

        return boardRepository.countByType(boardType);
    }
}
