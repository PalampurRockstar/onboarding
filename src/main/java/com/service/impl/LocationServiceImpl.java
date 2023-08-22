package com.service.impl;


import com.model.table.Breeder;
import com.model.table.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.table.Location;
import com.repository.LocationRepository;
import com.service.LocationService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepo;

    @Override
    public List<Location> findAll() {
        return locationRepo.findAll();
    }

    public Location save(Location location) {
        return locationRepo.save(location);
    }

}
