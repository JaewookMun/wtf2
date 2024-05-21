package com.wtf2.erp.common.controller;

import com.wtf2.erp.common.util.WebPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/organization")
    public String organization() {

        return WebPage.ORGANIZATION.getPath();
    }
}
