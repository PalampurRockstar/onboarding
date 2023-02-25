package com.service;


import com.model.rest.SearchCriteria;
import com.model.table.Pet;

import java.util.List;

public interface PetService {

    List<?> findAll();

    Pet findById(String id);

    Pet save(Pet superHero);
    List<Pet> search(SearchCriteria criteria);

	Pet update(String id, Pet Pet);

    void delete(String id);

}
