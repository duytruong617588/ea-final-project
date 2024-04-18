package com.miu.estate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateRatingRequest {
	@JsonProperty("user_id")
	@NotNull(message = "ID of user rating require non-null")
	Long userId;

	@JsonProperty("agent_id")
	@NotNull(message = "ID of agent being rated require non-null")
	Long agentId;

	@JsonProperty("point")
	@NotNull(message = "ID of user rating require non-null")
	Integer point;
}
