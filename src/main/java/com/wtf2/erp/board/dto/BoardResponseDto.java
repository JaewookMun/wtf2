package com.wtf2.erp.board.dto;

import com.wtf2.erp.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String createdDate;
    private String createdBy;
    private String lastModifiedDate;
    private int viewCount;

    private List<BoardResponseDto> children = new ArrayList<>();

    public BoardResponseDto(Board board, List<Board> children) {
        convertFrom(board);

        if (!children.isEmpty()) {
            convertChildren(board.getChildren());
        }
    }

    public BoardResponseDto convertFrom(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createdDate = board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.createdBy = board.getCreatedBy();
        this.lastModifiedDate = board.getLastModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.viewCount = board.getViewCount();

        return this;
    }

    /**
     * 검색 대상의 한 단계 하위 까지만 탐색하기 위해
     * children의 타입을 변경할 때는 children에 null을 넣는다.
     * @param boardList
     */
    public void convertChildren(List<Board> boardList) {
        this.children = boardList.stream()
                .map(child -> new BoardResponseDto(child, new ArrayList<>()))
                .collect(Collectors.toList());
    }


}
