package com.kakaopay.common.model;

import com.kakaopay.common.exceptioin.ApiException;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiExceptionResponse implements Serializable {
    private static final long serialVersionUID = -8974926535676292027L;

    private List<ApiException> apiExceptionList;

    private HttpStatus httpStatus;

    public ApiExceptionResponse(){
        apiExceptionList = new ArrayList<>();
    }

    public List<ApiException> getApiExceptionList() {
        return apiExceptionList;
    }

    public void setApiExceptionList(List<ApiException> apiExceptionList) {
        this.apiExceptionList = apiExceptionList;
    }

    public void setApiException(ApiException apiException){
        apiExceptionList.add(apiException);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
