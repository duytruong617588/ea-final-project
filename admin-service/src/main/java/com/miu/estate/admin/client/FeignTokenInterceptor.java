package com.miu.estate.admin.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {
	@Autowired
	private HttpServletRequest request;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authToken != null && !authToken.isEmpty()) {
			requestTemplate.header(HttpHeaders.AUTHORIZATION, authToken);
		}
	}
}