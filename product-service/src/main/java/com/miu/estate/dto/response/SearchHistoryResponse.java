package com.miu.estate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.miu.estate.model.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchHistoryResponse {
    private Long userId;

    private JSONPObject params;
}
