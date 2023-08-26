package com.controller;


import com.model.table.Price;
import com.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private PriceService service;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Price price) {
        return ResponseEntity.ok().body(service.save(price));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Price Price) {
        return ResponseEntity.ok().body(service.update(id, Price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}

