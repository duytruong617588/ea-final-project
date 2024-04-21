package com.miu.estate.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResetPasswordRequest {
	@JsonProperty("new_password")
	private String newPassword;
}
