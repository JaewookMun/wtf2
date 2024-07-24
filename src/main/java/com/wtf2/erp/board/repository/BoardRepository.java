package com.wtf2.erp.board.repository;

import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Long countByType(BoardType type);

    // TODO : @EntityGraph를 사용할 경우 Lazy Fetch가 정상적으로 이루어지지 않음.
//    @EntityGraph(attributePaths = {"children", "content"})
    @Query("select b from Board b join fetch b.content join fetch b.children where b.type = :type")
    List<Board> findByType(@Param("type") BoardType type);

    List<Board> findByType(BoardType type, Pageable pageable);

    @EntityGraph(attributePaths = "content")
    @Override
    Optional<Board> findById(Long aLong);
}
