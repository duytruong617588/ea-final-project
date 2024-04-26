package com.ttd.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
	private Long id;
	private String comment;
	private int rating;
	private Boolean isApproved = false;
	private Long userId;
	private Long propertyId;
	private String createdDate;
	private String updatedDate;
	private boolean active;
	private String deletedDate;
}
