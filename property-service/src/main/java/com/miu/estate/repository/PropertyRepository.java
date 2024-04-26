package com.miu.estate.repository;

import com.miu.estate.model.Property;
import com.miu.estate.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByDeletedAtIsNull();

    @Modifying
    @Query(value = "UPDATE properties SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?1", nativeQuery = true)
    void softDeletePropertyById(Long id);

    @Query("SELECT p FROM properties p WHERE " +
            "(:location IS NULL OR p.location = :location) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:numberOfRooms IS NULL OR p.numberOfRooms = :numberOfRooms) " +
            "AND (:propertyType IS NULL OR p.propertyType = :propertyType) " +
            "AND (:bedrooms IS NULL OR p.bedrooms = :bedrooms) " +
            "AND (:bathrooms IS NULL OR p.bathrooms = :bathrooms) " +
            "AND (:lounges IS NULL OR p.lounges = :lounges) " +
            "AND (:storeys IS NULL OR p.storeys = :storeys) " +
            "AND (p.deletedAt IS NULL)"
    )
    List<Property> getAllByCondition(@Param("location") String location,
                                     @Param("minPrice") Long minPrice,
                                     @Param("maxPrice") Long maxPrice,
                                     @Param("numberOfRooms") Integer numberOfRooms,
                                     @Param("propertyType") PropertyType propertyType,
                                     @Param("bedrooms") Integer bedrooms,
                                     @Param("bathrooms") Integer bathrooms,
                                     @Param("lounges") Integer lounges,
                                     @Param("storeys") Integer storeys);

}