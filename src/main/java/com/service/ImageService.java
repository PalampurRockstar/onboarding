package com.service;


import com.model.rest.SearchCriteria;
import com.model.table.Image;
import com.model.table.Pet;

import java.util.List;

public interface ImageService {

    List<?> findAll();

    Image findById(String id);

    Image save(Image image);

	Image update(String id, Image image);

    void delete(String id);

}
