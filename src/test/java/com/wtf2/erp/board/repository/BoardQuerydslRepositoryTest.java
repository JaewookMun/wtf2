package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.company.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class BoardQuerydslRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    BoardQuerydslRepository querydslRepository;

    @Test
    void test() {
        Company company = companyRepository.findAll().stream().findAny().get();

        List<Board> rootList = querydslRepository.findSubPages(BoardType.PAGE, 3L, company);
        System.out.println("size: " + rootList.size());
        for (Board board : rootList) {
            System.out.println("board = " + board.getTitle());
        }

    }

}