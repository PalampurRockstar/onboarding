package com.controller;


import com.model.table.Document;
import com.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService service;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Document Document) {
        return ResponseEntity.ok().body(service.save(Document));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Document Document) {
        return ResponseEntity.ok().body(service.update(id, Document));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}

