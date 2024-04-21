package com.miu.estate.admin.controller;

import com.miu.estate.admin.service.AdminReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class AdminReviewController {
	private final AdminReviewService adminReviewService;

	@PutMapping("/{id}/approval/{isApprove}")
	public ResponseEntity<?> approveReview(@PathVariable Long id, @PathVariable Boolean isApprove) {
		return ResponseEntity.ok(adminReviewService.setReviewApproval(id, isApprove));
	}
}
