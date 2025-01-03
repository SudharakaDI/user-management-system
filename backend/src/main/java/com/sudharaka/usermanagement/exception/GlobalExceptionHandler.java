package com.sudharaka.usermanagement.exception;

import com.sudharaka.usermanagement.exception.pojo.SWErrorResponse;
import com.sudharaka.usermanagement.exception.pojo.SWExceptionCode;
import com.sudharaka.usermanagement.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<SWErrorResponse> noResourceFoundException(NoResourceFoundException ex) {
        log.error("No resource found exception occurs: {}", ex.getMessage());
        SWErrorResponse errorResponse = new SWErrorResponse(
                SWExceptionCode.UMSW002.toString(),
                SWExceptionCode.UMSW002.getErrorType(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<SWErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Method argument not valid exception occurs: {}", ex.getMessage());
        String message;
        FieldError fieldError = ex.getFieldError();
        if (fieldError != null){
            message = fieldError.getDefaultMessage();
        }else {
            message = ex.getMessage();
        }
        SWErrorResponse errorResponse = new SWErrorResponse(
                SWExceptionCode.UMSW003.toString(),
                SWExceptionCode.UMSW003.getErrorType(),
                message
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SWException.class)
    public ResponseEntity<SWErrorResponse> userManagementExceptionHandler(SWException ex) {
        log.error("User management error occurred. errorCode : {} , errorMessage : {}", ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(new SWErrorResponse(ex.getErrorCode(), ex.getErrorType(), ex.getErrorMessage()), ex.getStatusCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<SWErrorResponse> handleException(Exception ex) {
        log.error("Unexpected exception occurs.}% %{ERROR: {}", ex.getMessage());
        SWErrorResponse errorResponse = new SWErrorResponse(
                SWExceptionCode.UMSW001.toString(),
                SWExceptionCode.UMSW001.getErrorType(),
                Constant.ERROR_TYPE_SERVER);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
