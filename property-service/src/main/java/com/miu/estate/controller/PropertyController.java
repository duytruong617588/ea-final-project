package com.miu.estate.controller;

import com.miu.estate.client.UserClient;
import com.miu.estate.dto.request.CreatePropertyRequest;
import com.miu.estate.dto.request.ImageRequest;
import com.miu.estate.dto.response.PropertyResponse;
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
	public ResponseEntity<List<PropertyResponse>> getAll(
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

	@GetMapping("/{id}")
	public ResponseEntity<Property> getOne(@PathVariable Long id) {
		return propertyService.getOne(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody CreatePropertyRequest p, HttpServletRequest request) {
		return propertyService.create(p)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody CreatePropertyRequest p, HttpServletRequest request) {
		return propertyService.update(id, p)
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
