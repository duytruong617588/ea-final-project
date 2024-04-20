package com.ttd.core.model.propertypaging;

import java.util.List;

import com.ttd.core.model.Property;
import lombok.Data;

@Data
public class PropertyPaging{
	private int number;
	private boolean last;
	private int numberOfElements;
	private int size;
	private int totalPages;
	private Pageable pageable;
	private List<SortItem> sort;
	private List<Property> content;
	private boolean first;
	private int totalElements;
	private boolean empty;
}