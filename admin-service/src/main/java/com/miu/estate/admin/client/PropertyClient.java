package com.miu.estate.admin.client;

import com.ttd.core.model.Property;
import com.ttd.core.model.Review;
import com.ttd.core.model.propertypaging.PropertyPaging;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "property-service")
public interface PropertyClient {
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/properties/list")
	PropertyPaging getAll();

	Review setReviewApproval(Long id, Boolean isApprove);

	Property changePublishStatusProperty(Long id, String status);
}