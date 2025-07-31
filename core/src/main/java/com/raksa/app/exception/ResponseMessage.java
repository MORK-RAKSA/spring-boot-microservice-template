package com.raksa.app.exception;

import com.raksa.app.constants.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Objects;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMessage<T> {
    private String statusCode;
    private String message;
    private String code;
    private String pathURL;
    private String traceId;
    private T data;

    public static <T> ResponseMessage<T> error(String statusCode, String message, String code, String pathURL, String traceId){
        return ResponseMessage.<T>builder()
                .statusCode(statusCode)
                .code(code)
                .message(message)
                .pathURL(pathURL)
                .traceId(traceId)
                .build();
    }

    public static <T> ResponseMessage<T> success(String message,T data){

        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder
                        .getRequestAttributes()))
                .getRequest();

        return ResponseMessage.<T>builder()
                .statusCode(HttpStatus.SUCCESSFULLY)
                .code("SUC200")
                .message(message)
                .data(data)
                .pathURL(request.getRequestURI())
                .traceId(MDC.get("traceId"))
                .build();
    }

    public static <T> ResponseMessage<T> success(T data){

        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder
                        .getRequestAttributes()))
                .getRequest();

        return ResponseMessage.<T>builder()
                .statusCode(HttpStatus.SUCCESSFULLY)
                .code("SUC200")
                .message("Successfully")
                .data(data)
                .pathURL(request.getRequestURI())
                .traceId(MDC.get("traceId"))
                .build();
    }
}
