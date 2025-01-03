package com.sudharaka.usermanagement.exception;

import com.sudharaka.usermanagement.exception.pojo.SWExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SWException extends Exception {

    private final HttpStatus statusCode;
    private final String errorCode;
    private final String errorType;
    private final String errorMessage;

    public SWException(HttpStatus statusCode,
                       SWExceptionCode errorCode,
                       String errorMessage) {
        this.statusCode = statusCode;
        this.errorCode = errorCode.name();
        this.errorType = errorCode.getType();
        this.errorMessage = errorMessage;
    }

}
