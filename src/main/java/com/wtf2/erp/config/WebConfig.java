package com.wtf2.erp.config;

import com.wtf2.erp.config.interceptor.RegisteredUserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final RegisteredUserInterceptor registeredUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(registeredUserInterceptor)
                .excludePathPatterns("/login",
                        "/modules/**",
                        "/css/**", "/js/**", "/images/**",
                        "/users/registration", "/groups/registration");
    }

}
