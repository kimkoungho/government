package com.kakaopay.common.exceptioin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"cause", "stackTrace", "message", "localizedMessage", "suppressed"}, ignoreUnknown = true)
public class ApiException extends RuntimeException {
    private String exceptionCode;

    private String exceptionMessage;

    public ApiException(String exceptionCode, String exceptionMessage){
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public ApiException(ApiExceptionCode apiExceptionCode){
        this.exceptionCode = apiExceptionCode.getCode();
        this.exceptionMessage = apiExceptionCode.getMessage();
    }

    public ApiException(ApiExceptionCode apiExceptionCode, String target, String key){
        this.exceptionCode = apiExceptionCode.getCode();
        this.exceptionMessage = String.format(apiExceptionCode.getMessage(), target, key);
    }

    @JsonProperty("exception")
    public String getException(){
        return this.getCause() != null ?  this.getCause().getClass().getName() : this.getClass().getName();
    }


    public String getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
