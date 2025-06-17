package com.microservices.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    private static final String RESPONSE_HEADER = "X-Response-Time";

    @Bean
    public RouteLocator ezyPay(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("accounts_route", r -> r
                        .path("/ezypay/accounts/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/accounts/(?<accountSegment>.*)", "/${accountSegment}")
                                .filter((exchange, chain) -> {
                                    exchange.getResponse()
                                            .getHeaders()
                                            .add(RESPONSE_HEADER, LocalDateTime.now().toString());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://ACCOUNTS"))
                .route("loans_route", r -> r
                        .path("/ezypay/loans/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/loans/(?<loanSegment>.*)", "/${loanSegment}")
                                .filter((exchange, chain) -> {
                                    exchange.getResponse()
                                            .getHeaders()
                                            .add(RESPONSE_HEADER, LocalDateTime.now().toString());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://LOANS"))
                .route("cards_route", r -> r
                        .path("/ezypay/cards/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/cards/(?<cardSegment>.*)", "/${cardSegment}")
                                .filter((exchange, chain) -> {
                                    exchange.getResponse()
                                            .getHeaders()
                                            .add(RESPONSE_HEADER, LocalDateTime.now().toString());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://CARDS"))
                .build();
    }

}
