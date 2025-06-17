package com.microservices.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator ezyPay(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("accounts_route", r -> r
                        .path("/ezypay/accounts/**")
                        .filters(f -> f.rewritePath("/ezypay/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNTS"))
                .route("loans_route", r -> r
                        .path("/ezypay/loans/**")
                        .filters(f -> f.rewritePath("/ezypay/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOANS"))
                .route("cards_route", r -> r
                        .path("/ezypay/cards/**")
                        .filters(f -> f.rewritePath("/ezypay/cards/(?<segment>.*)", "/${segment}"))
                        .uri("lb://CARDS"))
                .build();
    }
}
