package com.wtf2.erp.common.controller;

import com.wtf2.erp.common.util.WebPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnonymousWebController {

    @GetMapping("/login")
    public String login() {
        return WebPage.LOGIN.getPath();
    }

    @GetMapping("/groups/registration")
    public String addGroup() {
        return WebPage.GROUP_REGISTRATION.getPath();
    }

}
