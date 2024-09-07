package com.wtf2.erp.config.interceptor;

import com.wtf2.erp.config.security.form.AppUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RegisteredUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if(userDetails.getGroup() == null) {
            response.sendRedirect("/groups/registration");

            return false;
        }

        return true;
    }
}
