package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Long countByType(BoardType type);
    List<Board> findByType(BoardType type, Pageable pageable);
}
