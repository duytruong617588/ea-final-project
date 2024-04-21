package com.miu.estate.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResetPasswordRequest {
	@JsonProperty("new_password")
	private String newPassword;
}
