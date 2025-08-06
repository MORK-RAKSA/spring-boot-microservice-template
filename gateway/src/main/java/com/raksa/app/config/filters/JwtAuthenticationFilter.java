//package com.raksa.app.config.filters;
//
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//
//import java.util.List;
//
//@Component
//public class JwtAuthenticationFilter implements GlobalFilter {
//    private static final List<String> openEndpoints = List.of(
//            "/login",
//            "/register",
//            "/v3/api-docs/**"
////            "/swagger-ui.html",
////            "/webjars/",        // Swagger assets
////            "/swagger-resources"
//    );
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String path = exchange.getRequest().getPath().toString();
//        // Allow open endpoints to pass without JWT check
//        if (openEndpoints.stream().anyMatch(path::startsWith)) {
//            return chain.filter(exchange);
//        }
//
//        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//
//        return chain.filter(exchange);
//    }
//}
