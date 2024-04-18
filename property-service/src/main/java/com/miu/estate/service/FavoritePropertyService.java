package com.miu.estate.service;

import com.miu.estate.dto.request.FavoritePropertyRequest;
import com.miu.estate.model.FavoriteProperty;
import com.miu.estate.model.Property;
//import com.miu.estate.model.User;
import com.miu.estate.repository.FavoritePropertyRepository;
import com.miu.estate.repository.PropertyRepository;
//import com.miu.estate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FavoritePropertyService {
//    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final FavoritePropertyRepository favoritePropertyRepository;

    public Optional<List<FavoriteProperty>> getFavoritePropertyByUserId(Long userId) {
//        User u = userRepository.findById(userId).orElse(null);
//        if (u == null) {
//            return Optional.empty();
//        }
        return Optional.of(favoritePropertyRepository.findAllByUserId(userId));
    }

    public void addFavoriteProperty(FavoritePropertyRequest f) {
        Property p = propertyRepository.findById(f.getPropertyId()).orElse(null);
//        User u = userRepository.findById(f.getUserId()).orElse(null);
//        if (p == null || u == null) {
//            return;
//        }
        FavoriteProperty favoriteProperty = new FavoriteProperty();
//        favoriteProperty.setUser(u);
//        favoriteProperty.setProperty(p);
        favoritePropertyRepository.save(favoriteProperty);
    }

    public void deleteFavoriteProperty(Long id) {
        favoritePropertyRepository.deleteById(id);
    }
}
