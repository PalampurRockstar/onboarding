package com.service.impl;


import com.model.rest.SearchCriteria;
import com.model.table.Breeder;
import com.model.table.Image;
import com.repository.BreederRepository;
import com.repository.ImageRepository;
import com.repository.PetRepository;
import com.repository.filter.PetSpecification;
import com.service.ImageService;
import com.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepo;
    @Override
    public List<Image> findAll() {
        return imageRepo.findAll();
    }

    @Override
    public Image findById(String id) {
        return imageRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Image not found for id :: " + id));
    }

    @Override
    public Image save(Image pet) {
        return imageRepo.save(pet);
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
