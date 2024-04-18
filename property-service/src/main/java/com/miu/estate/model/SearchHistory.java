package com.miu.estate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(columnDefinition = "JSON")
    private String params;
}
