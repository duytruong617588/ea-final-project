package com.miu.estate.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ImageResponse {
    private Boolean success;
    private String message;
    private String url;
    private String description;
}
