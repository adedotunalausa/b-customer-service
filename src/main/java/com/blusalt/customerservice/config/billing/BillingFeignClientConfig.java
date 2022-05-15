package com.blusalt.customerservice.config.billing;

import com.blusalt.customerservice.helpers.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class BillingFeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new BillingFeignRequestInterceptor();
    }

    @Bean
    public ErrorDecoder errorDecoder () {
        return new FeignErrorDecoder();
    }

}
