package com.service;


import com.model.table.Review;

import java.util.List;

public interface ReviewService {

    List<?> findAll();

    Review findById(String id);

    Review save(Review Review);

	Review update(String id, Review Review);

    void delete(String id);

}
