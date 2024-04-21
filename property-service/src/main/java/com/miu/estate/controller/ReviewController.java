package com.miu.estate.controller;

import com.miu.estate.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
	private final ReviewService reviewService;
	@PutMapping("/{id}/approval/{isApprove}")
	public ResponseEntity<?> approveReview(@PathVariable Long id, @PathVariable Boolean isApprove) {
		return ResponseEntity.ok(reviewService.setReviewApproval(id,isApprove));
	}

}
