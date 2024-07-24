package com.wtf2.erp.common.controller;

import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.service.BoardService;
import com.wtf2.erp.common.util.WebPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("home");

        model.addAttribute("pages", boardService.list(BoardType.PAGE));



        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return WebPage.LOGIN.getPath();
    }

    @GetMapping("/notice-board")
    public String boardPage() {
        return WebPage.NOTICE_BOARD.getPath();
    }

    @GetMapping("/notice-board/register")
    public String registerPage() { return WebPage.NOTICE_REGISTER.getPath();}

    @GetMapping("/notice-board/details/{boardId}")
    public String detailsPage(@PathVariable(name = "boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId);

        return WebPage.NOTICE_DETAILS.getPath();
    }

    @GetMapping("/organization")
    public String organization() {

        return WebPage.ORGANIZATION.getPath();
    }

}
