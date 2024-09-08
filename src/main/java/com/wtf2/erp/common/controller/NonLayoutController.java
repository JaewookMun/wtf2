package com.wtf2.erp.common.controller;

import com.wtf2.erp.common.util.WebPage;
import com.wtf2.erp.config.security.form.AppUserDetails;
import com.wtf2.erp.group.dto.GroupInfo;
import com.wtf2.erp.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NonLayoutController {

    private final GroupService groupService;

    @GetMapping("/login")
    public String login() {
        return WebPage.LOGIN.getPath();
    }

    @GetMapping("/groups/registration")
    public String addGroup() {
        return WebPage.GROUP_REGISTRATION.getPath();
    }
    @PostMapping(value = "/groups/registration")
    public String registerGroup(@RequestParam(name = "groupName") String name) {
//        log.info("groupName: {}", name);
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Long groupId = groupService.register(name, userDetails.getUsername());
        userDetails.setGroupInfo(new GroupInfo(groupId, name));

        return "redirect:/";
    }
}
