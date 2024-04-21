package com.miu.estate.admin.service;

import com.miu.estate.admin.client.PropertyClient;
import com.ttd.core.model.Property;
import com.ttd.core.model.propertypaging.PropertyPaging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminPropertyService {
	private final PropertyClient propertyClient;

	public PropertyPaging getAll() {
		return propertyClient.getAll();
	}

	public Property changePublishStatusProperty(Long id, String status) {
		return propertyClient.changePublishStatusProperty(id, status);
	}
}
