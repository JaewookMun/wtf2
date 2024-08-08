package com.wtf2.erp.board.service;

import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    @DisplayName("페이지 조회")
    void test() {
        List<BoardResponseDto> rootList = boardService.getSubPageList(BoardType.PAGE, null);

        if (!rootList.isEmpty())
            assertThat(rootList.get(0).getTitle()).isNotBlank();
    }

    // TODO : 페이지 삭제 시 쿼리 여러개 발생 (children 조회.)
    // TODO : 연관관계로 인한 추가 쿼리 방지 cf. BulkDelete, @EntityGraph (fetch join)
    @WithUserDetails(value = "test")
    @Test
    @DisplayName("페이지 삭제")
    public void tdd() throws Exception {
        //given
        Long id = 3L;

        //when
        boardService.deletePage(id);

        //then
        int subCount = boardService.getSubPageList(BoardType.PAGE, id).size();

        assertThat(subCount).isEqualTo(0);
    }

}