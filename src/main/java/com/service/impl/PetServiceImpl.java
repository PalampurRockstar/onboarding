package com.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.dto.PetDto;
import com.model.table.Breeder;
import com.repository.BreederRepository;
import com.repository.ImageRepository;
import lombok.var;
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
import java.util.Optional;

import static com.model.mapper.PetMapper.petMapperInstance;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepo;
    @Autowired
    private BreederRepository breederRepo;

    @Autowired
    private ImageRepository imageRepo;

    @Override
    public List<Pet> findAll() {
        return petRepo.findAll();
    }

    @Override
    public PetDto findById(String id) {
        return petRepo.findById(id)
                .map(petMapperInstance::petToPetDtoWithGetterSetter)
                .map(p->{
                    imageRepo.findDPImageHavingPetId(p.getId()).ifPresent(p::setProfilePicture);
                    return p;
                })
                .orElseThrow(() -> new RuntimeException ("** Pet not found for id :: " + id));
    }

    @Override
    public Pet save(Pet pet) {
        Optional.of(pet)
                .map(Pet::getBreeder)
                .map(Breeder::getId).flatMap(id -> breederRepo
                        .findById(id)).ifPresent(b -> b.getPets()
                        .add(pet));

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
