package com.ttd.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class ApiiGatewayHealthCheck {
    @RequestMapping("/check")
    public String healthCheck() {
        return "OK";
    }
}
