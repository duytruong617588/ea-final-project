package com.miu.estate.service;

import com.miu.estate.client.UserClient;
import com.miu.estate.exception.InvalidTokenException;
import com.miu.estate.model.FavoriteProperty;
import com.miu.estate.model.Property;
import com.miu.estate.repository.FavoritePropertyRepository;
import com.ttd.core.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Getter
@Setter
public class FavoritePropertyService {
    private final FavoritePropertyRepository favoritePropertyRepository;
    private final UserClient userClient;
    private final HttpServletRequest request;

    public void addFavoriteProperty(Long propertyId) {
        User user = userClient.getUserByToken(request.getHeader("Authorization"));
        if (user == null) {
            throw new InvalidTokenException("User not found");
        }
        FavoriteProperty favoriteProperty = new FavoriteProperty();
        favoriteProperty.setUserId(user.getId());
        favoriteProperty.setPropertyId(propertyId);
        favoritePropertyRepository.save(favoriteProperty);
    }

    public void removeFavoriteProperty(Long propertyId) {
        User user = userClient.getUserByToken(request.getHeader("Authorization"));
        if (user == null) {
            throw new InvalidTokenException("User not found");
        }
        FavoriteProperty favoriteProperty = favoritePropertyRepository.findByUserIdAndPropertyId(user.getId(), propertyId);
        favoritePropertyRepository.delete(favoriteProperty);
    }

    public List<FavoriteProperty> getFavoriteProperties() {
        User user = userClient.getUserByToken(request.getHeader("Authorization"));
        if (user == null) {
            throw new InvalidTokenException("User not found");
        }
        List<FavoriteProperty> favoriteProperties = favoritePropertyRepository.findByUserId(user.getId());
        return favoriteProperties;
    }
}
