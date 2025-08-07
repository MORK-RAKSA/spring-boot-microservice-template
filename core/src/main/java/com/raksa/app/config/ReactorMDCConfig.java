package com.raksa.app.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Hooks;

@Configuration
public class ReactorMDCConfig {
    @PostConstruct
    public void init() {
        // Enable MDC context propagation for Reactor
        Hooks.enableAutomaticContextPropagation();
    }
}
