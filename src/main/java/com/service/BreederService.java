package com.service;


import com.model.rest.SearchCriteria;
import com.model.table.Breeder;
import com.model.table.Breeder;

import java.util.List;

public interface BreederService {

    List<?> findAll();

    Breeder findById(String id);

    Breeder save(Breeder superHero);


	Breeder update(String id, Breeder Breeder);

    void delete(String id);

}
