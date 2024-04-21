package com.miu.estate.admin.controller;

import com.miu.estate.admin.client.PropertyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private final PropertyClient propertyClient;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(propertyClient.getAll());
    }

    @PutMapping("/review/{id}/approval/{isApprove}")
    public ResponseEntity<?> approveReview(@PathVariable Long id, @PathVariable Boolean isApprove) {
        return ResponseEntity.ok(propertyClient.setReviewApproval(id,isApprove));
    }


    @PutMapping("/property/{id}/approve/{status}")
    public ResponseEntity<?> approvePropertyPost(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(propertyClient.changePublishStatusProperty(id, status));
    }
}
