package com.wtf2.erp.board.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wtf2.erp.board.domain.Board;
import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.company.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.wtf2.erp.board.domain.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public List<Board> findSubPages(BoardType type, Long parentId, Company company) {
        return queryFactory
                .selectFrom(board)
                .leftJoin(board.children).fetchJoin()
                .where(
                        board.type.eq(type),
                        parentIdEq(board.parent.id, parentId),
                        board.company.eq(company)
                )
                .fetch();
    }

    private BooleanExpression parentIdEq(NumberPath<Long> id, Long parentId) {
        return parentId != null ? id.eq(parentId) : id.isNull();
    }
}
