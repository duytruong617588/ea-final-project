package com.miu.estate.controller;

import com.miu.estate.dto.request.FavoritePropertyRequest;
import com.miu.estate.model.FavoriteProperty;
import com.miu.estate.repository.FavoritePropertyRepository;
import com.miu.estate.service.FavoritePropertyService;
import com.miu.estate.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/favorite-properties")
public class FavoritePropertyController {
    private final FavoritePropertyService favoritePropertyService;

//    @GetMapping
//    public ResponseEntity<List<FavoriteProperty>> getFavoritePropertyByUserId(HttpServletRequest request) {
//        Long userId = userLogin.orElseThrow(() -> new RuntimeException("User not found!")).getId();
//        return favoritePropertyService.getFavoritePropertyByUserId(userId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.badRequest().build());
//    }

    @PostMapping
    public void addFavoriteProperty(@RequestBody FavoritePropertyRequest f, HttpServletRequest request) {
        favoritePropertyService.addFavoriteProperty(f);
    }

    @DeleteMapping("/{id}")
    public void deleteFavoriteProperty(@PathVariable Long id) {
        favoritePropertyService.deleteFavoriteProperty(id);
    }
}
