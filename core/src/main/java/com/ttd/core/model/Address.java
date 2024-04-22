package com.ttd.core.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
}
