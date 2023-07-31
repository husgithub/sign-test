package com.example.signprovider.exception;

public class BaseResult<T> {
    private String code = "1";
    private String message;
    private T body;

    public BaseResult() {
        super();
    }

    public BaseResult(T body) {
        super();
        this.body = body;
    }

    public static BaseResult success(Object body) {
        BaseResult result = new BaseResult(body);
        result.setCode(BaseErrorCode.SUCCESS.getCode());
        result.setMessage(BaseErrorCode.SUCCESS.getMessage());
        return result;
    }

    public static BaseResult error(ErrorCodeInterface errorCode) {
        BaseResult result = new BaseResult();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getMessage());
        return result;
    }

    public static BaseResult error(String code,String message) {
        BaseResult result = new BaseResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
