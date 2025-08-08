package com.raksa.app.config;

import com.raksa.app.utils.RoleAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/webjars/**",
                                "/login",
                                "/get-user-by-username",
                                "/create-user",
                                "/h2-console"
                        ).permitAll()
                        .anyExchange().hasAnyRole("SUPERADMIN")
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(RoleAccess.grantedAuthoritiesExtractor()))
                );
        return http.build();
    }

//    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//        return jwt -> {
//            String role = jwt.getClaimAsString("role"); // get the role from JWT
//            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
//            return Mono.just(new JwtAuthenticationToken(jwt, List.of(authority)));
//        };
//    }
}
