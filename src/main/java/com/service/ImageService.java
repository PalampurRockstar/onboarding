package com.service;


import com.model.rest.SearchCriteria;
import com.model.table.Document;
import com.model.table.Image;
import com.model.table.Pet;

import java.util.List;

public interface ImageService {

    public List<Image> findAll();

    public Image findById(String id);

    public Image save(Image image);

    public List<Image> findByPetId(String id);

    public Image update(String id, Image image);

    public void delete(String id);

}
