package com.service.impl;


import com.model.table.Review;
import com.model.table.Pet;
import com.repository.ReviewRepository;
import com.repository.PetRepository;
import com.service.ReviewService;
import com.service.ReviewService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private PetRepository petRepo;
    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    public Review findById(String id) {
        return reviewRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Review not found for id :: " + id));
    }

    @Override
    public Review save(Review review){
        var createdReview=reviewRepo.save(review);
        Optional
                .ofNullable(review)
                .map(Review::getPet)
                .map(Pet::getId)
                .ifPresent((id)->petRepo
                        .findById(id)
                        .ifPresent(petRepo::save)
                );
        return createdReview;
    }

    @Override
    public Review update(String id, Review Review) {
    	reviewRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Review not found for id :: " + id));
    	Review.setId(id);
    	return reviewRepo.save(Review);
    }

    @Override
    public void delete(String id) {
        reviewRepo.findById(id).ifPresent(Review -> reviewRepo.delete(Review));
    }
}
