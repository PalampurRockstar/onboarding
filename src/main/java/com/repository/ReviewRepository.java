package com.repository;


import com.model.table.Image;
import com.model.table.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String>, JpaSpecificationExecutor<Review> {}
