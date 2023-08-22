package com.controller;


import com.model.table.Image;
import com.model.table.Review;
import com.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Review review) {
        return ResponseEntity.ok().body(service.save(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Review review) {
        return ResponseEntity.ok().body(service.update(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}

