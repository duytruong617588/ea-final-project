package com.miu.estate.repository;

import com.miu.estate.model.FavoriteProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritePropertyRepository extends JpaRepository <FavoriteProperty, Long>{
    FavoriteProperty findByUserIdAndPropertyId(Long userId, Long propertyId);

    List<FavoriteProperty> findByUserId(Long userId);

    List<FavoriteProperty> findAllByPropertyId(Long propertyId);
}
