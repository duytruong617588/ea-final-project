package com.miu.estate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "feature_id")
	private Feature feature;

//	@OneToMany
//	@JoinColumn(name = "property_id")
//	private List<Image> images;
//
//	@OneToMany
//	private List<Review> reviews;
}