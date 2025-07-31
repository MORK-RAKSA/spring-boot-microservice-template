package com.raksa.app.exception;

import com.raksa.app.constants.HttpStatus;
import com.raksa.app.exception.exceptionHandle.*;
import com.raksa.app.exception.exceptionHandle.IllegalArgumentException;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseMessage<Object> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        String traceId = MDC.get("traceId");
        return ResponseMessage.builder()
                .statusCode(HttpStatus.RESOURCE_NOT_FOUND)
                .code("ERROR_404")
                .message(ex.getMessage())
                .pathURL(request.getRequestURI())
                .traceId(traceId)
                .build();
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseMessage<Object> handleDuplicated(DuplicateEntityException ex, HttpServletRequest request){
        String traceId = MDC.get("traceId");
        return ResponseMessage.builder()
                .statusCode(HttpStatus.DUPLICATE_ENTRY)
                .code("ERROR_409")
                .message(ex.getMessage())
                .pathURL(request.getRequestURI())
                .traceId(traceId)
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseMessage<Object> handleException(Exception ex, HttpServletRequest request){
        String traceId = MDC.get("traceId");
        return ResponseMessage.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .code("ERROR_500")
                .message("Unexpected Error: " + ex.getMessage())
                .pathURL(request.getRequestURI())
                .traceId(traceId)
                .build();
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseMessage<Object> handlePersistenceException(PersistenceException ex, HttpServletRequest request){
        String traceId = MDC.get("traceId");
        return ResponseMessage.builder()
                .statusCode(HttpStatus.VALIDATION_ERROR)
                .code("ERROR_400")
                .message("Database error: " + ex.getMessage())
                .pathURL(request.getRequestURI())
                .traceId(traceId)
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseMessage<Object> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request){
        String traceId = MDC.get("traceId");
        return ResponseMessage.builder()
                .statusCode(HttpStatus.VALIDATION_ERROR)
                .code("ERROR_400")
                .message("Invalid argument: " + ex.getMessage())
                .pathURL(request.getRequestURI())
                .traceId(traceId)
                .build();
    }
}