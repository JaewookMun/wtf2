package com.wtf2.erp.common.advice;

import com.wtf2.erp.common.dto.JsonResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public JsonResponse<?> handleDataIntegrityViolationException(Exception e) {
        e.printStackTrace();

        return JsonResponse.fail()
                .message(e.getMessage())
                .buildWith(null);
    }
}