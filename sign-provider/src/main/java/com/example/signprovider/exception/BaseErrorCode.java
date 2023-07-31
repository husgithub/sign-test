package com.example.signprovider.exception;

public enum BaseErrorCode implements ErrorCodeInterface {
    SUCCESS("0", "成功"),
    ERROR("1000", "失败"),
    DEFAULT_ERROR("100000", "{0}"),
    RSP_UNKNOWN_ERROR("0600", "未知错误");

    private String code;
    private String message;

    BaseErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


    public static BaseErrorCode getEnumByCode(String code) {
        for (BaseErrorCode item : BaseErrorCode.values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return RSP_UNKNOWN_ERROR;
    }
}
