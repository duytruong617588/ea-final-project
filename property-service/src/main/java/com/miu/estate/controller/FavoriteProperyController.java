package com.miu.estate.controller;

import com.miu.estate.client.UserClient;
import com.miu.estate.exception.InvalidTokenException;
import com.miu.estate.service.FavoritePropertyService;
import com.ttd.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/favorite-properties")
public class FavoriteProperyController {
    private final FavoritePropertyService favoritePropertyService;

    @GetMapping
    public void getFavoriteProperties() {
        favoritePropertyService.getFavoriteProperties();
    }

    @PostMapping("/add")
    public void addFavoriteProperty(Long propertyId) {
        favoritePropertyService.addFavoriteProperty(propertyId);
    }

    @DeleteMapping("/remove/{propertyId}")
    public void removeFavoriteProperty(@PathVariable Long propertyId) {
        favoritePropertyService.removeFavoriteProperty(propertyId);
    }
}
