package com.service;


import com.model.table.Review;

import java.util.List;

public interface ReviewService {

    public List<?> findAll();

    public Review findById(String id);

    public List<Review> findByPetId(String id);

    public Review save(Review Review);

    public Review update(String id, Review Review);

    public void delete(String id);

}
