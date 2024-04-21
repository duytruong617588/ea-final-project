package com.miu.estate.service;

import com.miu.estate.client.UserClient;
import com.miu.estate.dto.request.CreatePropertyRequest;
import com.miu.estate.dto.request.ImageRequest;
import com.miu.estate.dto.response.PropertyResponse;
import com.miu.estate.exception.PropertyNotFoundException;
import com.miu.estate.exception.PublishStatusPropertyInvalidException;
import com.miu.estate.model.Image;
import com.miu.estate.model.Property;
import com.miu.estate.model.PropertyType;
import com.miu.estate.model.PublishStatus;
import com.miu.estate.repository.ImageRepository;
import com.miu.estate.repository.PropertyRepository;
//import com.miu.estate.repository.UserRepository;
import com.ttd.core.model.User;
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

	public Page<Property> getAll(Pageable pageRequest) {
		return propertyRepository.findAll(pageRequest);
	}
	public List<PropertyResponse> getProperties(){
		List<PropertyResponse> propertyResponses = new ArrayList<>();
		this.propertyRepository.findAllByDeletedAtIsNull()
			.forEach(property -> {
				List<String> images = new ArrayList<>();
				property.getImages().forEach(image1 -> images.add(image1.getDescription()));
				propertyResponses.add(PropertyResponse.getPropertyResponse(images, property));
			});
		return propertyResponses;
	}

	public Optional<Property> create(CreatePropertyRequest p) {
		User user = userClient.getUserByToken();
		List<Image> images = new ArrayList<>();
		for (ImageRequest imageRequest : p.getImages()) {
			Image image = new Image();
			image.setUrl(imageRequest.getUrl());
			image.setDescription(imageRequest.getDescription());
			images.add(image);
		}

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
		if (images.size() > 0) {
			property.setImages(images);
		}
		property.setUserId(user.getId());
		return Optional.of(propertyRepository.save(property));
	}

	public Optional<Property> getOne(Long id) {
		return propertyRepository.findById(id);
	}

	public Optional<Property> update(Long id, CreatePropertyRequest p) {
		User user = userClient.getUserByToken();
		if (user == null) {
			throw new PropertyNotFoundException("User not found!");
		}
		Property currentProperty = propertyRepository.getOne(id);
		if (currentProperty == null) {
			throw new PropertyNotFoundException("Property not found!");
		}
		List<Image> images = new ArrayList<>();
		for (ImageRequest imageRequest : p.getImages()) {
			Image image = new Image();
			image.setUrl(imageRequest.getUrl());
			image.setDescription(imageRequest.getDescription());
			images.add(image);
		}

		Property property = new Property();
		property.setId(id);
		property.setLocation(p.getLocation() != null ? p.getLocation() : currentProperty.getLocation());
		property.setPrice(p.getPrice() != null ? p.getPrice() : currentProperty.getPrice());
		property.setNumberOfRooms(p.getNumberOfRooms() != null ? p.getNumberOfRooms() : currentProperty.getNumberOfRooms());
		property.setPropertyType(p.getPropertyType() != null ? p.getPropertyType() : currentProperty.getPropertyType());

		property.setStoreys(p.getStoreys());
		property.setLounges(p.getLounges());
		property.setBathrooms(p.getBathrooms());
		property.setBedrooms(p.getBedrooms());
		property.setImages(images.size() > 0 ? images : currentProperty.getImages());
		property.setUserId(user.getId());
		return Optional.of(propertyRepository.save(property));
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
		return userClient.getUsers();
	}

	public Property changePublishStatusProperty(Long id, String status) {
		var property = propertyRepository.findById(id).orElseThrow(new PropertyNotFoundException());
		var publishStatus = PublishStatus.find(status).orElseThrow(new PublishStatusPropertyInvalidException());
		property.setPublishStatus(publishStatus);
		return propertyRepository.save(property);
	}
}
