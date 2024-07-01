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

    // TODO : OAuth를 활용한 사용자 등록기능 추가
    @PostMapping("/registration")
    public JsonResponse<Long> registerUser(@RequestParam(name = "companyId") Long companyId,
                                           @RequestParam(name = "userName") String userName,
                                           @RequestParam(name = "email") String email,
                                           @RequestParam(name = "password") String password) {

        return JsonResponse
                .succeed()
                .buildWith(userService.register(companyId, userName, email, password));
    }
}
