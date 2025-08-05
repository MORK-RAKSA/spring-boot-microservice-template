//package com.raksa.app.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.MDC;
//
//import java.io.IOException;
//
//@Slf4j
//public class TraceIdFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        try {
//            String traceId = java.util.UUID.randomUUID().toString().replace("-", "");
//            MDC.put("traceId", traceId);
//            servletRequest.setAttribute("traceId", traceId);
//            filterChain.doFilter(servletRequest, servletResponse);
//        }finally {
//            MDC.remove("traceId");
//        }
//    }
//}
