package com.repository;


import com.model.table.Image;
import com.model.table.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String>, JpaSpecificationExecutor<Review> {
    List<Review> findByPetId(String id);
}
