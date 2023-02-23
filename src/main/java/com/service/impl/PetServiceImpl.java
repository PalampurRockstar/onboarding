package com.service.impl;


import com.model.Pet;
import com.repository.PetRepository;
import com.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository repository;

    @Override
    public List<Pet> findAll() {
        return repository.findAll();
    }

    @Override
    public Pet findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException ("** Pet not found for id :: " + id));
    }

    @Override
    public Pet save(Pet Pet) {
        return repository.save(Pet);
    }

    @Override
    public Pet update(String id, Pet Pet) {
    	repository.findById(id).orElseThrow(() -> new RuntimeException ("** Pet not found for id :: " + id));
        
    	Pet.setId(id);
    	return repository.save(Pet);
    }

    @Override
    public void delete(String id) {
        repository.findById(id).ifPresent(Pet -> repository.delete(Pet));
    }
}
