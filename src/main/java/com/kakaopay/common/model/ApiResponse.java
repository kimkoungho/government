package com.kakaopay.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kakaopay.common.exceptioin.ApiException;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// 공통 format
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1610015895270798239L;

    private HttpStatus httpStatus;

    private T data;

    private List<ApiException> apiExceptionList;

    public ApiResponse(){
        apiExceptionList = new ArrayList<>();
    }


    public int getHttpCode() {
        return httpStatus.value();
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ApiException> getApiExceptionList() {
        return apiExceptionList;
    }

    public void setApiExceptionList(List<ApiException> apiExceptionList) {
        this.apiExceptionList = apiExceptionList;
    }
}
