package com.miu.estate.service;

import com.miu.estate.client.UserClient;
import com.miu.estate.dto.response.PropertyResponse;
import com.miu.estate.exception.PropertyNotFoundException;
import com.miu.estate.exception.PublishStatusPropertyInvalidException;
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
//				property.getImages().forEach(image1 -> images.add(image1.getDescription()));
				propertyResponses.add(PropertyResponse.getPropertyResponse(images, property));
			});
		return propertyResponses;
	}

	public Optional<Property> create(Property p) {
//		User user = userRepository.save(p.getPropertyOwner());
//		List<Image> images = new ArrayList<>();
//		p.getImages().forEach(image -> images.add(imageRepository.save(image)));
//		p.setFeature(feature);
//		p.setImages(images);
//		p.setPropertyOwner(user);
		return Optional.of(propertyRepository.save(p));
	}

	public Optional<Property> getOne(Long id) {
		return propertyRepository.findById(id);
	}

	public Optional<Property> update(Long id, Property p) {
		Property existingProperty = propertyRepository.findById(id).orElse(null);
		if (existingProperty != null) {
			return Optional.of(propertyRepository.save(p));
		} else {
			return Optional.empty();
		}
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
