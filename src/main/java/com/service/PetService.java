package com.service;



import com.model.Pet;

import java.util.List;

public interface PetService {

    List<?> findAll();

    Pet findById(String id);

    Pet save(Pet superHero);

	Pet update(String id, Pet Pet);

    void delete(String id);

}
