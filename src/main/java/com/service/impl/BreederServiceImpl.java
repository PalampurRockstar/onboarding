package com.service.impl;



import com.model.table.Breeder;
import com.model.table.Image;
import com.repository.BreederRepository;
import com.service.BreederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreederServiceImpl implements BreederService {

    @Autowired
    private BreederRepository repository;

    @Override
    public List<Breeder> findAll() {
        return repository.findAll();
    }

    @Override
    public Breeder findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException ("** Breeder not found for id :: " + id));
    }

    public Breeder findByPetId(String id){
        return repository.findPetId(id);
    }
    @Override
    public Breeder save(Breeder Breeder) {
        return repository.save(Breeder);
    }



    @Override
    public Breeder update(String id, Breeder Breeder) {
    	repository.findById(id).orElseThrow(() -> new RuntimeException ("** Breeder not found for id :: " + id));
        
    	Breeder.setId(id);
    	return repository.save(Breeder);
    }

    @Override
    public void delete(String id) {
        repository.findById(id).ifPresent(Breeder -> repository.delete(Breeder));
    }
}
