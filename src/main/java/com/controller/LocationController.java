package com.controller;


import com.model.rest.SearchCriteria;
import com.model.table.Location;
import com.model.table.Pet;
import com.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService service;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping()
    public Location save(@RequestBody Location location) {
        return service.save(location);
    }

}

