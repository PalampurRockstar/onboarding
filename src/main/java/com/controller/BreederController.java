package com.controller;


import com.model.rest.SearchCriteria;
import com.model.table.Breeder;
import com.service.BreederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/breeder")
public class BreederController {
    @Autowired
    private BreederService service;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> findImagesByBreederId(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findImagesByBreederId(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Breeder Breeder) {
        return ResponseEntity.ok().body(service.save(Breeder));
    }

    

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Breeder Breeder) {
        return ResponseEntity.ok().body(service.update(id, Breeder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}

