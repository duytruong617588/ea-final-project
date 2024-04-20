package com.ttd.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Property{
	private String description;
	private int numberOfRooms;
	private int storeys;
	private int bathrooms;
	private int userId;
	private int bedrooms;
	private String createdAt;
	private Object deletedAt;
	private int price;
	private int lounges;
	private String propertyType;
	private String location;
	private int id;
	private String publishStatus;
	private String updatedAt;
	private String status;
}