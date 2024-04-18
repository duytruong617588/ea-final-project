package com.miu.estate.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "properties")
public class Property extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	private String location;
	private Integer numberOfRooms;
	private PropertyType propertyType;
	private PropertyStatus status;
	private Long price;
	private PublishStatus publishStatus;

	private Long userId;

	private int bathrooms;
	private int bedrooms;
	private int lounges;
	private int storeys;

//	@OneToMany
//	@JoinColumn(name = "property_id")
//	private List<Image> images;
//
//	@OneToMany
//	private List<Review> reviews;
}