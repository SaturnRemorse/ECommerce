package com.saturn.ecommerce.api_gateway.filters;

import com.saturn.ecommerce.api_gateway.service.JwtService;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private final JwtService jwtService;

    public AuthenticationGatewayFilterFactory(JwtService jwtService) {
        super(Config.class);
        this.jwtService= jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {

        if(!config.isEnabled){
            return (exchange, chain) -> {
                return chain.filter(exchange);
            };
        }
        return (exchange, chain) ->{

            String authorizationHeaders = exchange.getRequest().getHeaders().getFirst("Authorization");
            if(authorizationHeaders == null){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String token = authorizationHeaders.split("Bearer ")[1];

            Long userId = jwtService.getUserIdFromToken(token);
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header("X-USER-ID", userId.toString())
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }

    @Data
    public static class Config{
        private boolean isEnabled;
    }
}
