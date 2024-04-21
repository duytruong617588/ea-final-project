package com.ttd.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private Long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private Boolean isApproved = false;
	private Boolean active = true;

	private String role;
	private String status;
//
//	private Address address;
}