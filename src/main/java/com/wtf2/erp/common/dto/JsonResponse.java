package com.wtf2.erp.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JsonResponse<T> {

    private static String RESULT_SUCCESS = "SUCCESS";
    private static String RESULT_FAIL = "FAIL";

    private String result;
    private String message;

    private T data;

    public static <T> DataBuilder<T> succeed() {
        return new DataBuilder<>(RESULT_SUCCESS);
    }

    public static <T> DataBuilder<T> fail() {
        return new DataBuilder<>(RESULT_FAIL);
    }

    public static <T> DataBuilder<T> fail(String message) {
        return new DataBuilder<>(RESULT_FAIL);
    }

    @NoArgsConstructor
    public static class DataBuilder<T> {
        private String result;
        private String message;

        public DataBuilder(String result) {
            this.result = result;
        }

        public DataBuilder message(String message) {
            this.message = message;
            return this;
        }

        public <T> JsonResponse<T> buildWith(@Nullable T data) {
            return new JsonResponse<>(result, message, data);
        }
    }
}