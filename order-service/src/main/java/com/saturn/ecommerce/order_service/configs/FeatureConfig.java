package com.saturn.ecommerce.order_service.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class FeatureConfig {

    @Value("${feature.user-tracking}")
    private boolean isUserTrackingAvailable;
}
