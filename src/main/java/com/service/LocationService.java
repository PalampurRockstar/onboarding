package com.service;


import com.model.table.Location;

import java.util.List;

public interface LocationService {

    List<?> findAll();
    Location save(Location location);

}
