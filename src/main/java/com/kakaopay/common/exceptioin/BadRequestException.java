package com.kakaopay.common.exceptioin;

public class BadRequestException extends ApiException {

    public BadRequestException(ApiExceptionCode apiExceptionCode, String target, String key) {
        super(apiExceptionCode, target, key);
    }
}
