package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.PageContent;
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
    public void tdd() throws Exception {
        //given

        //when
        List<PageContent> page = repository.findByBoardIdOrderByIndex(2L);

        //then
    }
}