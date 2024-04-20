package com.ttd.core.model.propertypaging;

import lombok.Data;

@Data
public class SortItem{
	private String nullHandling;
	private boolean ignoreCase;
	private String property;
	private boolean ascending;
	private boolean descending;
	private String direction;
}