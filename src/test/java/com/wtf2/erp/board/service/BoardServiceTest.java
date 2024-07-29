package com.wtf2.erp.board.service;

import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    void test() {
        List<BoardResponseDto> rootList = boardService.getPageList(BoardType.PAGE, null);
    }
}