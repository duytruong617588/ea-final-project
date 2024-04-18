package com.miu.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeatureRequest {
    private int bathrooms;
    private int bedrooms;
    private int lounges;
    private int storeys;
}
