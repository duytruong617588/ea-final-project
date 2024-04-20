package com.ttd.core.model.propertypaging;

import java.util.List;
import lombok.Data;

@Data
public class Pageable{
	private boolean paged;
	private int pageNumber;
	private int offset;
	private int pageSize;
	private boolean unpaged;
	private List<SortItem> sort;
}