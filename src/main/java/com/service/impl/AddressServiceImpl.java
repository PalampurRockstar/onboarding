package com.service.impl;


import com.model.table.Address;
import com.model.table.Location;
import com.repository.AddressRepository;
import com.repository.LocationRepository;
import com.service.AddressService;
import com.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository locationRepo;

    @Override
    public List<Address> findAll() {
        return locationRepo.findAll();
    }

    public Address save(Address address) {
        return locationRepo.save(address);
    }

}
