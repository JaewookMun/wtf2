package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardRequestDto;
import com.wtf2.erp.board.service.BoardService;
import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.company.repository.CompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    CompanyRepository companyRepository;

    @Test
    @DisplayName("EntityGraph test")
    void test() {

        BoardRequestDto dto = new BoardRequestDto();
        dto.setType(BoardType.NOTICE.name());
        dto.setTitle("[notice] 공지사항1");
        dto.setContent("안녕하세요\n공지사항입니다.");

        boardService.post(dto);

        boardRepository.flush();
        Board board = boardRepository.findById(1L).get();
        System.out.println("board = " + board);
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
        Company company = companyRepository.findAll().stream().findAny().get();

        Board parent = new Board("super", BoardType.PAGE, company);

        Board sub1 = new Board("sub1", BoardType.PAGE, company);
        Board sub2 = new Board("sub2", BoardType.PAGE, company);
        Board sub3 = new Board("sub3", BoardType.PAGE, company);
        parent.getChildren().add(sub1);
        parent.getChildren().add(sub2);
        parent.getChildren().add(sub3);

        boardRepository.save(parent);
    }


}