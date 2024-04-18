package com.miu.estate.model;

import java.util.Optional;

public enum PublishStatus {
	PENDING, APPROVED, REJECTED;

	public static Optional<PublishStatus> find(String value) {
		if (value.equals(PENDING.name())) {
			return Optional.of(PENDING);
		} else if (value.equals(APPROVED.name())) {
			return Optional.of(APPROVED);
		} else if (value.equals(REJECTED.name())) {
			return Optional.of(REJECTED);
		} else {
			return Optional.empty();
		}
	}
}
