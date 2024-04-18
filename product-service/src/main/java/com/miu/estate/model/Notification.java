package com.miu.estate.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Notification {
	String title;
	String body;

	@Override
	public String toString() {
		return String.format("{title: %s, body: %s}", title, body);
	}
}
