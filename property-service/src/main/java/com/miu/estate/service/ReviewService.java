package com.miu.estate.service;

import com.miu.estate.exception.PropertyReviewNotFoundException;
import com.miu.estate.model.Review;
import com.miu.estate.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	public Review setReviewApproval(Long id, Boolean isApprove) {
		var review = reviewRepository.findById(id).orElseThrow(new PropertyReviewNotFoundException());
		review.setIsApproved(isApprove);
		reviewRepository.save(review);
		return reviewRepository.save(review);
	}
}
