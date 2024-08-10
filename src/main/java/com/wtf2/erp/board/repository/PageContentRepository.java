package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.PageContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageContentRepository extends JpaRepository<PageContent, Long> {

    List<PageContent> findByBoardIdOrderByIndex(Long boardId);

    void deleteByBoardId(Long boardId);
}
