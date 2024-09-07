package com.wtf2.erp.user.controller;

import com.wtf2.erp.common.dto.JsonResponse;
import com.wtf2.erp.common.util.WebPage;
import com.wtf2.erp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public JsonResponse<Long> registerUser(@RequestParam(name = "userName") String userName,
                                           @RequestParam(name = "email") String email,
                                           @RequestParam(name = "password") String password) {

        return JsonResponse
                .succeed()
                .buildWith(userService.register(userName, email, password));
    }
}
