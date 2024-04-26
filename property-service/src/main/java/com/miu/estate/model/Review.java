package com.miu.estate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private int rating;

    @Column(name = "is_approved")
    private Boolean isApproved = false;

    private Long userId;

    private Long propertyId;
    private String createdDate;
    private String updatedDate;
    private boolean active;
    private String deletedDate;
}
