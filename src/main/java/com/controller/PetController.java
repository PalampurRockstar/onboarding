package com.controller;


import com.service.PriceService;
import com.service.PetService;
import com.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.rest.SearchCriteria;
import com.model.table.Pet;


import java.util.List;


@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(petService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(petService.findById(id));
    }
    @GetMapping("/{petId}/review")
    public ResponseEntity<?> findReviewByPetId(@PathVariable String petId) {
        return ResponseEntity.ok().body(reviewService.findByPetId(petId));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pet Pet) {
        return ResponseEntity.ok().body(petService.save(Pet));
    }

    @PostMapping("/search")
    public List<Pet> save(@RequestBody SearchCriteria criteria) {
        return petService.search(criteria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Pet Pet) {
        return ResponseEntity.ok().body(petService.update(id, Pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        petService.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}

