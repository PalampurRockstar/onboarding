package com.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.rest.SearchCriteria;
import com.model.table.Breeder;
import com.model.table.Image;
import com.model.table.Pet;
import com.repository.BreederRepository;
import com.repository.ImageRepository;
import com.repository.PetRepository;
import com.repository.filter.PetSpecification;
import com.service.ImageService;
import com.service.PetService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private PetRepository petRepo;
    @Override
    public List<Image> findAll() {
        return imageRepo.findAll();
    }

    @Override
    public Image findById(String id) {
        return imageRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Image not found for id :: " + id));
    }
    public List<Image> findByPetId(String id){
        return imageRepo.findByPetId(id);
    }
    @Override
    public Image save(Image image){
        var createdimage=imageRepo.save(image);
        Optional
                .ofNullable(image)
                .map(Image::getPet)
                .map(Pet::getId)
                .ifPresent((id)->petRepo
                        .findById(id)
                        .ifPresent(petRepo::save)
                );

        return createdimage;
    }

    @Override
    public Image update(String id, Image Image) {
    	imageRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Image not found for id :: " + id));
    	Image.setId(id);
    	return imageRepo.save(Image);
    }

    @Override
    public void delete(String id) {
        imageRepo.findById(id).ifPresent(Image -> imageRepo.delete(Image));
    }
}
