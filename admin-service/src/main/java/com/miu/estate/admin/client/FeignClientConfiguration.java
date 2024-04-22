package com.miu.estate.admin.client;

import com.ttd.core.feign.FeignTokenInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfiguration {
	private final HttpServletRequest request;
	@Bean
	public FeignTokenInterceptor feignTokenInterceptor() {
		return new FeignTokenInterceptor(request);
	}
}
