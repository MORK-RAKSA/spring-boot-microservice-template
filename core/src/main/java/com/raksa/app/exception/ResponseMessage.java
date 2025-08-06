package com.raksa.app.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.raksa.app.constants.HttpStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.time.LocalDateTime;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String statusCode;
    private String message;
    private String code;
    private String traceId;
    private String pathURI;
    private T data;


    public static <T> ResponseMessage<T> error(String statusCode, String message, String code, ServerHttpRequest request){
        return ResponseMessage.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(statusCode)
                .code(code)
                .message(message)
                .traceId(MDC.get("traceId"))
                .pathURI(request.getPath().pathWithinApplication().value())
                .build();
    }

    public static <T> ResponseMessage<T> success(String message,T data){
        return ResponseMessage.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.SUCCESSFULLY)
                .code("SUC200")
                .message(message)
                .data(data)
                .traceId(MDC.get("traceId"))
                .build();
    }

    public static <T> ResponseMessage<T> success(T data){
        return ResponseMessage.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.SUCCESSFULLY)
                .code("SUC200")
                .message("Successfully")
                .data(data)
                .traceId(MDC.get("traceId"))
                .build();
    }

    public static <T> ResponseMessage<T> success(String message){
        return ResponseMessage.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.SUCCESSFULLY)
                .code("SUC200")
                .message(message)
                .traceId(MDC.get("traceId"))
                .build();
    }
}
