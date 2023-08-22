package com.controller;


import com.model.table.Address;
import com.model.table.Location;
import com.service.AddressService;
import com.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService address;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(address.findAll());
    }

    @PostMapping()
    public Address save(@RequestBody Address location) {
        return address.save(location);
    }

}

