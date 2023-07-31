package com.example.signprovider.exception;

public class BaseException extends RuntimeException {

    private String code;
    private String message;

    public BaseException(ErrorCodeInterface errorCode) {
        super(errorCode.getCode());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BaseException(ErrorCodeInterface errorCode, String message) {
        super(errorCode.getCode());
        this.code = errorCode.getCode();
        this.message = message;
    }

    public BaseException(ErrorCodeInterface errorCode, Throwable cause) {
        super(errorCode.getCode(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
