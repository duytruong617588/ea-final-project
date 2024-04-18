package com.miu.estate.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritePropertyRequest {
    @NotNull(message = "PropertyId cannot empty")
    private Long propertyId;

    @Nullable
    private Long userId;
}
