package com.miu.estate.service;

import com.miu.estate.client.UserClient;
import com.miu.estate.dto.request.CreatePropertyRequest;
import com.miu.estate.dto.request.ImageRequest;
import com.miu.estate.dto.response.PropertyResponse;
import com.miu.estate.exception.PropertyNotFoundException;
import com.miu.estate.exception.PublishStatusPropertyInvalidException;
import com.miu.estate.model.*;
import com.miu.estate.repository.ImageRepository;
import com.miu.estate.repository.PropertyRepository;
//import com.miu.estate.repository.UserRepository;
import com.miu.estate.repository.ReviewRepository;
import com.ttd.core.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {
	private final PropertyRepository propertyRepository;
	private final ImageRepository imageRepository;
	private final UserClient userClient;
	private final ReviewRepository reviewRepository;
	private final HttpServletRequest request;

	public List<PropertyResponse> getAll(Pageable pageRequest) {
		List<PropertyResponse> propertyResponses = new ArrayList<>();
		var list = propertyRepository.findAll(pageRequest);
		List<User> users = userClient.getAllUsers();
		list.forEach(property -> {
			User owner = users.stream()
					.filter(user -> user.getId().equals(property.getUserId()))
					.findFirst()
					.orElse(null);
			List<Image> images = imageRepository.findByPropertyId(property.getId());
			List<Review> reviews = reviewRepository.findByPropertyId(property.getId());
			propertyResponses.add(PropertyResponse.getPropertyResponse(images, property, owner, reviews));
		});
		return propertyResponses;
	}
	public List<PropertyResponse> getProperties(){
		List<PropertyResponse> propertyResponses = new ArrayList<>();
		this.propertyRepository.findAllByDeletedAtIsNull()
			.forEach(property -> {
				User owner = userClient.getUserDetailById(property.getUserId());
				List<Image> images = imageRepository.findByPropertyId(property.getId());
				List<Review> reviews = reviewRepository.findByPropertyId(property.getId());
				propertyResponses.add(PropertyResponse.getPropertyResponse(images, property, owner, reviews));
			});
		return propertyResponses;
	}

	public Optional<Property> create(CreatePropertyRequest p) {
		User user = userClient.getUserByToken(request.getHeader("Authorization"));
		Property property = new Property();
		property.setLocation(p.getLocation());
		property.setPrice(p.getPrice());
		if (p.getNumberOfRooms() != null) {
			property.setNumberOfRooms(p.getNumberOfRooms());
		}
		if (p.getPropertyType() != null) {
			property.setPropertyType(p.getPropertyType());
		}

		property.setStoreys(p.getStoreys());
		property.setLounges(p.getLounges());
		property.setBathrooms(p.getBathrooms());
		property.setBedrooms(p.getBedrooms());
		property.setUserId(user.getId());
		return Optional.of(propertyRepository.save(property));
	}

	public Optional<PropertyResponse> getOne(Long id) {
		Property property = propertyRepository.findById(id).orElse(null);
		if (property == null) {
			return Optional.empty();
		}
		User owner = userClient.getUserDetailById(property.getUserId());
		List<Image> images = imageRepository.findByPropertyId(property.getId());
		List<Review> reviews = reviewRepository.findByPropertyId(property.getId());
		PropertyResponse propertyResponse = PropertyResponse.getPropertyResponse(images, property, owner, reviews);
		return Optional.of(propertyResponse);
	}

	public Optional<Property> update(Long id, CreatePropertyRequest p) {
		User user = userClient.getUserByToken(request.getHeader("Authorization"));
		if (user == null) {
			throw new PropertyNotFoundException("User not found!");
		}
		Property currentProperty = propertyRepository.getOne(id);
		if (currentProperty == null) {
			throw new PropertyNotFoundException("Property not found!");
		}
//		Property property = new Property();
//		property.setId(id);
		currentProperty.setLocation(p.getLocation() != null ? p.getLocation() : currentProperty.getLocation());
		currentProperty.setPrice(p.getPrice() != null ? p.getPrice() : currentProperty.getPrice());
		currentProperty.setNumberOfRooms(p.getNumberOfRooms() != null ? p.getNumberOfRooms() : currentProperty.getNumberOfRooms());
		currentProperty.setPropertyType(p.getPropertyType() != null ? p.getPropertyType() : currentProperty.getPropertyType());

		currentProperty.setStoreys(p.getStoreys());
		currentProperty.setLounges(p.getLounges());
		currentProperty.setBathrooms(p.getBathrooms());
		currentProperty.setBedrooms(p.getBedrooms());
		currentProperty.setUserId(user.getId());
		return Optional.of(propertyRepository.save(currentProperty));
	}

	public void delete(Long id) {
		propertyRepository.softDeletePropertyById(id);
	}

	public Optional<List<Property>> search(String location, Long minPrice, Long maxPrice,
									Integer numberOfRooms, PropertyType propertyType,
									Integer bedrooms, Integer bathrooms,
									Integer lounges, Integer storeys) {
		List<Property> properties = propertyRepository.getAllByCondition(
				location, minPrice, maxPrice, numberOfRooms,
				propertyType, bedrooms, bathrooms, lounges, storeys);
		return Optional.of(properties);
	}

	public List<User> getUsers() {
		System.out.println(request.getHeader("Authorization"));
		return userClient.getUsers(request.getHeader("Authorization"));
	}

	public Property changePublishStatusProperty(Long id, String status) {
		var property = propertyRepository.findById(id).orElseThrow(new PropertyNotFoundException());
		var publishStatus = PublishStatus.find(status).orElseThrow(new PublishStatusPropertyInvalidException());
		property.setPublishStatus(publishStatus);
		return propertyRepository.save(property);
	}
}
