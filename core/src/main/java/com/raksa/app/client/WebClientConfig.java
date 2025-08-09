package com.raksa.app.client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .responseTimeout(Duration.ofSeconds(3))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(3, TimeUnit.SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(3, TimeUnit.SECONDS))
                );
    }

//    @Bean
//    public WebClient webClient (){
//        return WebClient.builder()
//                .filter((request, next) -> {
//                   String token = null;
//                   var authentication = SecurityContextHolder.getContext().getAuthentication();
//                   if(authentication instanceof JwtAuthenticationToken jwtToken) {
//                       token = jwtToken.getToken().getTokenValue();
//                   }
//
//                   if (token != null) {
//                       var filteredRequest = org.springframework.web.reactive.function.client.ClientRequest.from(request)
//                               .header("Authorization", "Bearer " + token)
//                               .build();
//                       return next.exchange(filteredRequest);
//                   }
//                   return next.exchange(request);
//                })
//                .build();
//    }

//    @Bean
//    public WebClient webClient(){
//        return WebClient.builder().build();
//    }

}
