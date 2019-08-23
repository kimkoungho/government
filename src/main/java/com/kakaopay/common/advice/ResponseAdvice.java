package com.kakaopay.common.advice;

import com.kakaopay.common.model.ApiExceptionResponse;
import com.kakaopay.common.model.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/** rest controller 요청에 대해서 api response 형식을 포맷팅 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // rest controller 항상 pass
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        ApiResponse<Object> response = new ApiResponse();
        if(body.getClass().isAssignableFrom(ApiExceptionResponse.class)){
            ApiExceptionResponse apiExceptionResponse = (ApiExceptionResponse) body;
            response.setHttpStatus(apiExceptionResponse.getHttpStatus());
            response.setApiExceptionList(apiExceptionResponse.getApiExceptionList());
        }else {
            response.setData(body);
            response.setHttpStatus(HttpStatus.OK);
        }

        return response;
    }
}
