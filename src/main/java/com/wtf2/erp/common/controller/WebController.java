package com.wtf2.erp.common.controller;

import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardResponseDto;
import com.wtf2.erp.board.service.BoardService;
import com.wtf2.erp.common.util.WebPage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardService boardService;

    @ModelAttribute(name = "pages")
    public List<BoardResponseDto> pageMenuSetup(@RequestParam(required = false) Long parentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken) return null;

        return boardService.getPageList(BoardType.PAGE, null);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pages", boardService.getPageList(BoardType.PAGE, null));

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
