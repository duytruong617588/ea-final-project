package com.miu.estate.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class FeatureResponse {
    private int bathrooms;
    private int bedrooms;
    private int lounges;
    private int storeys;
}
