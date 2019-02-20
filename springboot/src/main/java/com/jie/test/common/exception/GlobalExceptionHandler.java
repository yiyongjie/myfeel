package com.jie.test.common.exception;

import com.jie.test.common.model.APIResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public APIResponse<String> exceptionHandler(HttpServletRequest request, RuntimeException exception) {
        return handleErrorInfo(request, exception.getMessage(), exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APIResponse<String> exceptionHandler(HttpServletRequest request, Exception exception) {
        return handleErrorInfo(request, exception.getMessage(), exception);
    }

    private APIResponse<String> handleErrorInfo(HttpServletRequest request, String message, Exception exception) {
        APIResponse<String> response = new APIResponse<>();
        String ERROR_CODE = "500";
        response.setCode(ERROR_CODE);
        response.setData("URL:" + request.getRequestURL().toString());
        response.setMsg(message);
        return response;
    }
}
