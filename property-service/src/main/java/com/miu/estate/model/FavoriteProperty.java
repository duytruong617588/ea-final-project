package com.miu.estate.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FavoriteProperty extends BaseEntity{
    private Long userId;
    private Long propertyId;
}
