package com.microservices.gatewayserver.filters.custom;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ResponseHeaderFilter {

    private static final String RESPONSE_HEADER = "X-Response-Time";

    public GatewayFilter addResponseHeader() {
        return (exchange, chain) -> {
            exchange.getResponse()
                    .getHeaders()
                    .add(RESPONSE_HEADER, LocalDateTime.now().toString());
            return chain.filter(exchange);
        };
    }
}