package com.miu.estate.admin.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
	@Bean
	public FeignTokenInterceptor feignTokenInterceptor() {
		return new FeignTokenInterceptor();
	}
}
