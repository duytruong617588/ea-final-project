package com.miu.estate.controller;

import com.miu.estate.dto.request.CreatePropertyRequest;
import com.miu.estate.dto.request.ImageRequest;
import com.miu.estate.model.Image;
import com.miu.estate.model.Property;
import com.miu.estate.model.PropertyType;
import com.miu.estate.service.PropertyService;
import com.ttd.core.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
	private final PropertyService propertyService;

	@GetMapping("/test")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(propertyService.getUsers());
	}

	@GetMapping("/list")
	public ResponseEntity<Page<Property>> getAll(
			@RequestParam Optional<Integer> pageNumber,
			@RequestParam Optional<String> sortBy,
			@RequestParam Optional<Integer> total
	) {
		Pageable pageRequest = PageRequest.of(
				pageNumber.orElse(0),
				total.orElse(10),
				Sort.Direction.ASC,
				sortBy.orElse("id")
		);
		return ResponseEntity.ok(propertyService.getAll(pageRequest));
	}

//	@GetMapping
//	public ResponseEntity<List<?>> getProperties() {
//		return ResponseEntity.ok(propertyService.getProperties());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Property> getOne(@PathVariable Long id) {
		return propertyService.getOne(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody CreatePropertyRequest p, HttpServletRequest request) {
//		Optional<User> userLogin = RequestUtil.getUserLogin(request);

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

		property.setStoreys(p.getFeature().getStoreys());
		property.setLounges(p.getFeature().getLounges());
		property.setBathrooms(p.getFeature().getBathrooms());
		property.setBedrooms(p.getFeature().getBedrooms());
//		if (images.size() > 0) {
//			property.setImages(images);
//		}
//		property.setPropertyOwner(userLogin.orElseThrow(() -> new RuntimeException("User not found!")));

		return propertyService.create(property)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody CreatePropertyRequest p, HttpServletRequest request) {
//		Optional<User> userLogin = RequestUtil.getUserLogin(request);
		Property currentProperty = propertyService.getOne(id).orElseThrow(() -> new RuntimeException("Property not found!"));
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

		property.setStoreys(p.getFeature().getStoreys());
		property.setLounges(p.getFeature().getLounges());
		property.setBathrooms(p.getFeature().getBathrooms());
		property.setBedrooms(p.getFeature().getBedrooms());
//		property.setImages(images.size() > 0 ? images : currentProperty.getImages());
//		property.setPropertyOwner(userLogin.orElseThrow(() -> new RuntimeException("User not found!")));
		return propertyService.update(id, property)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable Long id, HttpServletRequest request) {
		propertyService.delete(id);
	}

	@GetMapping("/search")
	public ResponseEntity<? extends List<?>> search(
			@RequestParam Optional<String> location,
			@RequestParam Optional<Long> minPrice,
			@RequestParam Optional<Long> maxPrice,
			@RequestParam Optional<Integer> numberOfRooms,
			@RequestParam Optional<PropertyType> propertyType,
			@RequestParam Optional<Integer> bedrooms,
			@RequestParam Optional<Integer> bathrooms,
			@RequestParam Optional<Integer> lounges,
			@RequestParam Optional<Integer> storeys
	) {
		var properties = propertyService.search(
				location.orElse(null),
				minPrice.orElse(null),
				maxPrice.orElse(null),
				numberOfRooms.orElse(null),
				propertyType.orElse(null),
				bedrooms.orElse(null),
				bathrooms.orElse(null),
				lounges.orElse(null),
				storeys.orElse(null)
		);
		return properties
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@PutMapping("/{id}/approve/{status}")
	public ResponseEntity<?> approvePropertyPost(@PathVariable Long id, @PathVariable String status) {
		return ResponseEntity.ok(propertyService.changePublishStatusProperty(id, status));
	}

}
