package com.repository;


import com.model.table.Breeder;
import com.model.table.Document;
import com.model.table.Image;
import com.model.table.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {
    List<Image> findByPetId(String id);
    @Query("select i from Image i inner join i.pet p where p.id  = :petId")
    List<Image> findImageByPetId(@Param("petId") String petId);

    @Query("select i from Image i inner join i.pet p where p.id  = :petId and is_profile_picture='true'")
    Optional<Image> findDPImageHavingPetId(@Param("petId") String petId);

    @Query("select i from Image i inner join i.breeder b where b.id  = :breederId and is_profile_picture='true'")
    Optional<Image> findDPImageHavingBreederId(@Param("breederId") String breederId);

    @Query("select i from Image i inner join i.breeder b where b.id  = :breederId")
    List<Image> findImagesHavingBreederId(@Param("breederId") String breederId);
}
