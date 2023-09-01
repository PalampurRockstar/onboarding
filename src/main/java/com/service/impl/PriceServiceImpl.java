package com.service.impl;


import com.model.table.Price;
import com.model.table.Pet;
import com.repository.PriceRepository;
import com.repository.PetRepository;
import com.service.PriceService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepo;
    @Autowired
    private PetRepository petRepo;
    @Override
    public List<Price> findAll() {
        return priceRepo.findAll();
    }
    public List<Price> findByPetId(String id){
        return priceRepo.findByPetId(id);
    }

    @Override
    public Price findById(String id) {
        return priceRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Price not found for id :: " + id));
    }

    @Override
    public Price save(Price image){
        var createdimage=priceRepo.save(image);
        Optional
                .of(image)
                .map(Price::getPet)
                .map(Pet::getId).flatMap(id -> petRepo
                        .findById(id)).ifPresent(petRepo::save);

        return createdimage;
    }

    @Override
    public Price update(String id, Price Price) {
    	priceRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Price not found for id :: " + id));
    	Price.setId(id);
    	return priceRepo.save(Price);
    }

    @Override
    public void delete(String id) {
        priceRepo.findById(id).ifPresent(Price -> priceRepo.delete(Price));
    }
}
