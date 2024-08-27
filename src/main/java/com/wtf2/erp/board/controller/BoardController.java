package com.wtf2.erp.board.controller;

import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.*;
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

    // left menu 관련
    // ================================================================================================

    /**
     * boardId = parent board id
     * 만약 상위 page가 존재하지 않을 경우 boardId = -1
     */
    @PostMapping("/page/{boardId}")
    public JsonResponse<Long> postPage(@PathVariable(name = "boardId") Long boardId) {
        log.info("boardId: {}", boardId);

        return JsonResponse.succeed()
                .buildWith(boardService.postPageFor(boardId));
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

    // page details 관련
    // ================================================================================================

    @GetMapping("/page/{boardId}")
    public JsonResponse<PageDetailsDto> pageDetails(@PathVariable(name = "boardId") Long id) {

        return JsonResponse.succeed()
                .buildWith(boardService.getPageDetails(id));
    }

    @PutMapping("/page/{boardId}/{lineId}")
    public JsonResponse<Boolean> updatePageLine(
            @PathVariable(name = "boardId") Long boardId,
            @PathVariable(name = "lineId") Long lineId,
            String lineContent) {

        System.out.println("lineContent = " + lineContent);
        PageLineUpdateDto request = new PageLineUpdateDto(boardId, lineId, lineContent);

        return JsonResponse.succeed()
                .buildWith(boardService.updatePageLine(request));
    }

    @PostMapping("/page/{boardId}/newline")
    public JsonResponse<Long> newline(@PathVariable(name = "boardId") Long boardId) {
        return JsonResponse.succeed()
                .buildWith(boardService.newPageLine(boardId));
    }


}
