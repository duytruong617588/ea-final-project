package com.miu.estate.dto.response;

import com.miu.estate.model.Property;
//import com.miu.estate.model.User;
//import com.miu.estate.repository.UserRepository;
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
    private Long ownerName;
    private String location;
    private String description;
    private Integer numberOfRooms;
    private String propertyType;
    private String status;
    private Long price;
    private FeatureResponse feature;
    private List<String> images;

    public static PropertyResponse getPropertyResponse(List<String> imageResponses,
                                                       Property property) {

        return PropertyResponse.builder()
                .id(property.getId())
                .description(property.getDescription())
                .location(property.getLocation())
                .numberOfRooms(property.getNumberOfRooms())
                .propertyType(String.valueOf(property.getPropertyType()))
                .price(property.getPrice())
                .status(String.valueOf(property.getStatus()))
                .feature(FeatureResponse.builder()
                        .bathrooms(property.getFeature().getBathrooms())
                        .bedrooms(property.getFeature().getBedrooms())
                        .storeys(property.getFeature().getStoreys())
                        .lounges(property.getFeature().getLounges())
                        .build())
                .images(imageResponses)
                .build();
    }
}
