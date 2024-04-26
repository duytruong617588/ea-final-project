package com.miu.estate.dto.response;

import com.miu.estate.model.Image;
import com.miu.estate.model.Property;
//import com.miu.estate.model.User;
//import com.miu.estate.repository.UserRepository;
import com.miu.estate.model.Review;
import com.ttd.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {
    private Long id;
    private Long ownerId;
    private String location;
    private String description;
    private Integer numberOfRooms;
    private String propertyType;
    private String status;
    private Long price;
    private FeatureResponse feature;
    private List<Image> images;
    private List<Review> reviews;
    private User owner;

    public static PropertyResponse getPropertyResponse(List<Image> imageResponses,
                                                       Property property,
                                                       User owner,
                                                       List<Review> reviews) {

        return PropertyResponse.builder()
                .id(property.getId())
                .ownerId(property.getUserId())
                .description(property.getDescription())
                .location(property.getLocation())
                .numberOfRooms(property.getNumberOfRooms())
                .propertyType(String.valueOf(property.getPropertyType()))
                .price(property.getPrice())
                .status(String.valueOf(property.getStatus()))
                .images(imageResponses)
                .owner(owner)
                .reviews(reviews)
                .build();
    }
}
