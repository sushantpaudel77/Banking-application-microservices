package com.microservices.gatewayserver.config;

import com.microservices.gatewayserver.filters.custom.ResponseHeaderFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    private final ResponseHeaderFilter responseHeaderFilter;

    public GatewayRoutesConfig(ResponseHeaderFilter responseHeaderFilter) {
        this.responseHeaderFilter = responseHeaderFilter;
    }

    @Bean
    public RouteLocator ezyPay(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("accounts_route", r -> r
                        .path("/ezypay/accounts/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/accounts/(?<accountsSegment>.*)", "/${accountsSegment}")
                                .filter(responseHeaderFilter.addResponseHeader()))
                        .uri("lb://ACCOUNTS"))
                .route("loans_route", r -> r
                        .path("/ezypay/loans/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/loans/(?<loansSegment>.*)", "/${loansSegment}")
                                .filter(responseHeaderFilter.addResponseHeader()))
                        .uri("lb://LOANS"))
                .route("cards_route", r -> r
                        .path("/ezypay/cards/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/cards/(?<cardsSegment>.*)", "/${cardsSegment}")
                                .filter(responseHeaderFilter.addResponseHeader()))
                        .uri("lb://CARDS"))
                .build();
    }
}
