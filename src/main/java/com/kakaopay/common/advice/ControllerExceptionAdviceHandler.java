package com.kakaopay.common.advice;

import com.kakaopay.common.exceptioin.ApiException;
import com.kakaopay.common.exceptioin.BadRequestException;
import com.kakaopay.common.model.ApiExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
@Order(999)
public class ControllerExceptionAdviceHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdviceHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiExceptionResponse defaultExceptionHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
        apiExceptionResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        if(e instanceof ApiException){
            apiExceptionResponse.setApiException((ApiException) e);
        }else{
            ApiException apiException = new ApiException(e.getClass().getName(), e.getMessage());
            apiExceptionResponse.setApiException(apiException);
        }

        return apiExceptionResponse;
    }

    // validation error
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiExceptionResponse validateExceptionHandler(BindException bindException){
        LOGGER.error("validation exception ... bindTarget: " + bindException.getBindingResult().getTarget(), bindException);

        List<ApiException> apiExceptionList = bindException.getAllErrors().stream()
                .map(error -> new ApiException(error.getCode(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
        apiExceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiExceptionResponse.setApiExceptionList(apiExceptionList);
        return apiExceptionResponse;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiExceptionResponse badExceptionHandler(BadRequestException badRequestException){
        LOGGER.error(badRequestException.getMessage(), badRequestException);

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
        apiExceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiExceptionResponse.setApiException(badRequestException);
        return apiExceptionResponse;
    }
}
