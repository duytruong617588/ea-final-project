package com.miu.estate.admin.service;

import com.miu.estate.admin.client.PropertyClient;
import com.ttd.core.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminReviewService {
	private final PropertyClient propertyClient;
	public Review setReviewApproval(Long id, Boolean isApprove) {
		return propertyClient.setReviewApproval(id, isApprove);
	}
}
