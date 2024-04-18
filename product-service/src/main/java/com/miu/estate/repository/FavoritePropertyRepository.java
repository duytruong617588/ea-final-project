package com.miu.estate.repository;

import com.miu.estate.model.FavoriteProperty;
import com.miu.estate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritePropertyRepository extends JpaRepository<FavoriteProperty, Long> {
    List<FavoriteProperty> findAllByUserId(Long id);
    List<FavoriteProperty> findAllByPropertyId(Long id);
}
