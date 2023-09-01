package com.service;


import com.model.dto.BreederDto;
import com.model.rest.SearchCriteria;
import com.model.table.Breeder;
import com.model.table.Breeder;
import com.model.table.Image;

import java.util.List;

public interface BreederService {

    public List<Breeder> findAll();

    public BreederDto findById(String id);
    public List<Image> findImagesByBreederId(String id);
    public Breeder save(Breeder superHero);
    public Breeder update(String id, Breeder Breeder);
    public void delete(String id);
    public Breeder findByPetId(String id);

}
