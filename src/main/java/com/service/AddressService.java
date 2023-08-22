package com.service;


import com.model.table.Address;
import com.model.table.Location;

import java.util.List;

public interface AddressService {

    List<?> findAll();
    Address save(Address location);

}
