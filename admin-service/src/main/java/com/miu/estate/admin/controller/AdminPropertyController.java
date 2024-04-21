package com.miu.estate.admin.controller;

import com.miu.estate.admin.service.AdminPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/properties")
public class AdminPropertyController {
    private final AdminPropertyService propertyService;

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(propertyService.getAll());
    }

    @PutMapping("/{id}/approve/{status}")
    public ResponseEntity<?> approvePropertyPost(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(propertyService.changePublishStatusProperty(id, status));
    }
}
