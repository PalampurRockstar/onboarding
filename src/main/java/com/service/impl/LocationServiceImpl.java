package com.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.table.Location;
import com.repository.LocationRepository;
import com.service.LocationService;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

}
