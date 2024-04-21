package com.miu.estate.dto.request;

import com.miu.estate.model.PropertyType;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePropertyRequest {
    @Nullable
    private  String description;
    @Nullable
    private  Integer numberOfRooms;
    @Nullable
    private  String Status;
    @Nullable
    private  Long price;
    @Nullable
    private  String location;
    @Nullable
    private PropertyType propertyType;
    @Nullable
    private int bathrooms;
    @Nullable
    private int bedrooms;
    @Nullable
    private int lounges;
    @Nullable
    private int storeys;
    @Nullable
    private List<ImageRequest> images;
}
