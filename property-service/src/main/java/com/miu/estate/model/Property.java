package com.miu.estate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	private int bathrooms;
	private int bedrooms;
	private int lounges;
	private int storeys;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Review> reviews;
}