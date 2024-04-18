package com.miu.estate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
//import com.miu.estate.model.User;
import io.swagger.v3.core.util.Json;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchHistoryRequest {
    @Nullable
    private Long userId;

    @Nullable
    @Column(columnDefinition = "JSON")
    @JsonProperty("params")
    private JsonNode params;
}
