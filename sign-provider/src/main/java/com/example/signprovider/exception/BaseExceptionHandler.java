package com.example.signprovider.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResult bizExceptionHandler(HttpServletRequest req, BaseException e) {
        return BaseResult.error(e.getCode(), e.getMessage());
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult exceptionHandler(HttpServletRequest req, Exception e) {
        return BaseResult.error(BaseErrorCode.ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public BaseResult bizExceptionHandler(HttpServletRequest req, RuntimeException e) {
        return BaseResult.error(BaseErrorCode.ERROR.getCode(), e.getMessage());
    }
}
