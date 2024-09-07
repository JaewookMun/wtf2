package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class BoardQuerydslRepositoryTest {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    BoardQuerydslRepository querydslRepository;

    @Test
    void test() {
        Group group = groupRepository.findAll().stream().findAny().get();

        List<Board> rootList = querydslRepository.findSubPages(BoardType.PAGE, 3L, group);
        System.out.println("size: " + rootList.size());
        for (Board board : rootList) {
            System.out.println("board = " + board.getTitle());
        }

    }

}