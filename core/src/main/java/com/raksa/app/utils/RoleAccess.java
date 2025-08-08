//package com.raksa.app.utils;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import reactor.core.publisher.Mono;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import java.util.List;
//
//public class RoleAccess {
//    public static Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//        return jwt -> {
//            String role = jwt.getClaimAsString("role"); // get the role from JWT
//            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
//            return Mono.just(new JwtAuthenticationToken(jwt, List.of(authority)));
//        };
//    }
//}
