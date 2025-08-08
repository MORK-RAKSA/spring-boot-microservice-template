//package com.raksa.app.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//
//@Slf4j
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig{
//
////    @Bean
////    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception{
////        http
////                .csrf(ServerHttpSecurity.CsrfSpec::disable)
////                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
////                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
////                .authorizeExchange(exchange -> exchange
////                        .pathMatchers(
////                                "/swagger-ui.html",
////                                "/swagger-ui/**",
////                                "/v3/api-docs/**",
////                                "/webjars/**",
////                                "/login"
////                        ).permitAll()
//////                        .pathMatchers("").authenticated()
////                    .anyExchange().permitAll()
////                )
////                .oauth2ResourceServer(oauth2-> oauth2
////                        .jwt(Customizer.withDefaults()));
////        log.info("Security configuration initialized successfully.");
////        return http.build();
////    }
//
//
//}