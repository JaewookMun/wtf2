package com.wtf2.erp.common.controller;

import com.wtf2.erp.board.domain.BoardType;
import com.wtf2.erp.board.dto.BoardResponseDto;
import com.wtf2.erp.board.service.BoardService;
import com.wtf2.erp.common.util.WebPage;
import com.wtf2.erp.config.security.form.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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
        System.out.println("pageMenuSetup()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) return null;

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        return boardService.getSubPageList(BoardType.PAGE, null, userDetails.getGroupInfo().getId());
    }

    @ModelAttribute(name = "groupName")
    public String groupName(Authentication authentication) {

        return ((AppUserDetails) authentication.getPrincipal()).getGroupInfo().getName();
    }

    @GetMapping("/")
    public String home() {
        System.out.println("home()");
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        return "home";
    }

    @GetMapping("/notice-board")
    public String noticeBoardPage() {
        return WebPage.NOTICE_BOARD.getPath();
    }

    @GetMapping("/notice-board/register")
    public String registerNotice() { return WebPage.NOTICE_REGISTER.getPath();}

    @GetMapping("/notice-board/details/{boardId}")
    public String noticeDetails(@PathVariable(name = "boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId);

        return WebPage.NOTICE_DETAILS.getPath();
    }

    @GetMapping("/pages/{boardId}/details")
    public String pageDetails(@PathVariable(name = "boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId);

        return WebPage.PAGE_DETAILS.getPath();
    }

    @GetMapping("/organization")
    public String organization() {

        return WebPage.ORGANIZATION.getPath();
    }

}
