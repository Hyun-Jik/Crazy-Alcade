package com.eni.backend.common.exception;

import com.eni.backend.common.response.BaseErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.eni.backend.common.response.BaseResponseStatus.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class, NoHandlerFoundException.class, TypeMismatchException.class})
    public BaseErrorResponse handleBadRequest(Exception e) {
        log.error("[BadRequestException]", e);
        return BaseErrorResponse.of(URL_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("[HttpRequestMethodNotSupportedException]", e);
        return BaseErrorResponse.of(METHOD_NOT_SUPPORTED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public BaseErrorResponse handleRuntimeException(Exception e) {
        log.error("[RuntimeException]", e);
        return BaseErrorResponse.of(SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomBadRequestException.class)
    protected BaseErrorResponse handleCustomBadRequestException(CustomBadRequestException e) {
        log.error("[CustomBadRequestException]");
        return BaseErrorResponse.of(e.getBaseResponseStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomServerErrorException.class)
    public BaseErrorResponse handleCustomServerErrorException(CustomServerErrorException e) {
        log.error("[CustomServerErrorException]", e);
        return BaseErrorResponse.of(e.getBaseResponseStatus());
    }

}
