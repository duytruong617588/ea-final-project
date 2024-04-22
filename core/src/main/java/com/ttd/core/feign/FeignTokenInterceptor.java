package com.ttd.core.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {
	private final HttpServletRequest request;

	public FeignTokenInterceptor(HttpServletRequest req) {
		request = req;
	}


	@Override
	public void apply(RequestTemplate requestTemplate) {
		String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authToken != null && !authToken.isEmpty()) {
			requestTemplate.header(HttpHeaders.AUTHORIZATION, authToken);
		}
	}
}