package com.miu.estate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
    private String createdDate;
    private String updatedDate;
    private boolean active;
    private boolean deleted;
    private String deletedDate;
}
