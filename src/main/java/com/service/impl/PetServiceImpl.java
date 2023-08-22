package com.service.impl;


import com.model.table.Breeder;
import com.repository.BreederRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.rest.SearchCriteria;
import com.model.table.Pet;
import com.repository.PetRepository;
import com.repository.filter.PetSpecification;
import com.service.PetService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepo;
    @Autowired
    private BreederRepository breederRepo;

    @Override
    public List<Pet> findAll() {
        return petRepo.findAll();
    }

    @Override
    public Pet findById(String id) {
        return petRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Pet not found for id :: " + id));
    }

    @Override
    public Pet save(Pet pet) {
        Breeder breeder=breederRepo.findById(pet.getBreeder().getId())  .orElseThrow(() -> new EntityNotFoundException("Breeder not found with id: " + pet.getBreeder().getId()));
        breeder.getPets().add(pet);
        return petRepo.save(pet);
    }
    @Override
    public List<Pet> search(SearchCriteria criteria) {
        return petRepo.findAll(new PetSpecification( criteria));
    }

    @Override
    public Pet update(String id, Pet pet) {
    	petRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Pet not found for id :: " + id));
    	pet.setId(id);
    	return petRepo.save(pet);
    }

    @Override
    public void delete(String id) {
        petRepo.findById(id).ifPresent(Pet -> petRepo.delete(Pet));
    }
}
