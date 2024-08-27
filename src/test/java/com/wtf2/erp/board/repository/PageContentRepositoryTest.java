package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.PageContent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class PageContentRepositoryTest {
    @Autowired
    PageContentRepository repository;

    @Test
    public void tdd1() throws Exception {
        //given

        //when
        List<PageContent> page = repository.findByBoardIdOrderByIndex(2L);

        //then
    }

    @Test
    @DisplayName("개별 페이지 컨텐츠 조회")
    void tdd2() {
        //given
        Long boardId = 3L;
        Long pageContentId = 5L;

        //when
        PageContent pageContent = repository.findByBoardIdAndId(boardId, pageContentId);
        System.out.println("pageContent = " + pageContent.getLine());

        //then

    }
}