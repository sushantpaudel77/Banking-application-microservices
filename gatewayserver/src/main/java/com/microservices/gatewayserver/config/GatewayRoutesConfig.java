package com.microservices.gatewayserver.config;

import com.microservices.gatewayserver.filters.custom.ResponseHeaderFilter;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;

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
                                .filter(responseHeaderFilter.addResponseHeader())
                                .circuitBreaker(config -> config.setName("accountsCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://ACCOUNTS"))
                .route("loans_route", r -> r
                        .path("/ezypay/loans/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/loans/(?<loansSegment>.*)", "/${loansSegment}")
                                .filter(responseHeaderFilter.addResponseHeader())
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000),
                                                2,true)))
                        .uri("lb://LOANS"))
                .route("cards_route", r -> r
                        .path("/ezypay/cards/**")
                        .filters(f -> f
                                .rewritePath("/ezypay/cards/(?<cardsSegment>.*)", "/${cardsSegment}")
                                .filter(responseHeaderFilter.addResponseHeader()))
                        .uri("lb://CARDS"))
                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
    }
}
