package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardRequestDto;
import com.wtf2.erp.board.service.BoardService;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.repository.GroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    GroupRepository groupRepository;

    @Test
    @DisplayName("EntityGraph test")
    void test() {
        // given
        BoardRequestDto dto = new BoardRequestDto();
        dto.setType(BoardType.NOTICE.name());
        dto.setTitle("[notice] 공지사항1");
        dto.setContent("안녕하세요\n공지사항입니다.");
        dto.setGroupId(1L);

        // when
        Long id = boardService.post(dto);

        boardRepository.flush();
        Board board = boardRepository.findById(id).get();
        System.out.println("board = " + board);

        // then
        assertEquals("[notice] 공지사항1", board.getTitle());
    }

    @Test
    @DisplayName("@EntityGraph with @Query")
    void test1() {
        List<Board> boardList = boardRepository.findRootByType(BoardType.PAGE);
        System.out.println("boardList.size() = " + boardList.size());
        for (Board board : boardList) {
            System.out.println("board.getTitle() = " + board.getTitle());
        }

    }


    @Test
    @DisplayName("Children")
    void test2() {
        Group group = groupRepository.findAll().stream().findAny().get();

        Board parent = new Board("super", BoardType.PAGE, group);

        Board sub1 = new Board("sub1", BoardType.PAGE, group);
        Board sub2 = new Board("sub2", BoardType.PAGE, group);
        Board sub3 = new Board("sub3", BoardType.PAGE, group);
        parent.getChildren().add(sub1);
        parent.getChildren().add(sub2);
        parent.getChildren().add(sub3);

        boardRepository.save(parent);
    }
}