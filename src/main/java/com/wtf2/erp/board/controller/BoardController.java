package com.wtf2.erp.board.controller;

import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardDetailsResponseDto;
import com.wtf2.erp.board.dto.BoardRequestDto;
import com.wtf2.erp.board.dto.BoardResponseDto;
import com.wtf2.erp.board.dto.PageDetailsDto;
import com.wtf2.erp.board.service.BoardService;
import com.wtf2.erp.common.dto.DataTableRequest;
import com.wtf2.erp.common.dto.DataTableResponse;
import com.wtf2.erp.common.dto.JsonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/total-count/{type}")
    public JsonResponse<Long> totalCount(@PathVariable(name = "type") String type) {

        return JsonResponse.succeed()
                .buildWith(boardService.totalCount(BoardType.valueOf(type.toUpperCase())));
    }

    @GetMapping("/list")
    public DataTableResponse<BoardResponseDto> list(@RequestParam(name = "type") String type,
                                                    @ModelAttribute @Valid DataTableRequest dataTableRequest) {
        BoardType boardType = BoardType.valueOf(type);

        return DataTableResponse.<BoardResponseDto>builder()
                .draw(dataTableRequest.getDraw())
                .data(boardService.list(boardType, dataTableRequest))
                .build();
    }

    @PostMapping
    public JsonResponse<Long> post(@RequestBody BoardRequestDto requestDto) {
        System.out.println("requestDto = " + requestDto);

        return JsonResponse.succeed()
                .buildWith(boardService.post(requestDto));
    }

    @GetMapping("/{id}")
    public JsonResponse<BoardDetailsResponseDto> detailsOf(@PathVariable(name = "id") Long id) {

        return JsonResponse.succeed()
                .buildWith(boardService.getBoardDetails(id));
    }

    // ================================================================================================

    @PostMapping("/page")
    public JsonResponse<Long> postPage(@RequestParam(required = false) Long parentId) {
        log.info("parentId: {}", parentId);

        return JsonResponse.succeed()
                .buildWith(boardService.postPageFor(parentId));
    }

    @GetMapping("/page/{id}")
    public JsonResponse<PageDetailsDto> pageDetails(@PathVariable(name = "id") Long id) {

        return JsonResponse.succeed()
                .buildWith(boardService.getPageDetails(id));
    }

    @GetMapping("/page/{boardId}/sub-items")
    public JsonResponse<List<BoardResponseDto>> subItems(@PathVariable(name = "boardId") Long id) {

        return JsonResponse.succeed().buildWith(boardService.getSubPageList(BoardType.PAGE, id));
    }

    @DeleteMapping("/page/{boardId}")
    public JsonResponse<String> deletePage(@PathVariable(name = "boardId") Long boardId) {

        boardService.deletePage(boardId);

        return JsonResponse.succeed().build();
    }

    @GetMapping("/page/{boardId}/newline")
    public JsonResponse<Long> newline(@PathVariable(name = "boardId") Long boardId) {
        return JsonResponse.succeed()
                .buildWith(boardService.newPageLine(boardId));
    }
}
