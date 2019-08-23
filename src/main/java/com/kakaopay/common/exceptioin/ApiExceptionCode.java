package com.kakaopay.common.exceptioin;

public enum ApiExceptionCode {
    NOT_FOUND_UPDATE_TARGET("NOT_FOUND_UPDATE_TARGET", "target: {}, key: {}")
    ;

    private String code;
    private String message;

    ApiExceptionCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
