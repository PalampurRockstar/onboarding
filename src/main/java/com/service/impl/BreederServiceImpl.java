package com.service.impl;



import com.model.dto.BreederDto;

import com.model.table.Breeder;
import com.model.table.Image;
import com.model.table.Pet;
import com.repository.BreederRepository;
import com.repository.ImageRepository;
import com.service.BreederService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.model.mapper.BreederMapper.breederMapperInstance;
@Service
public class BreederServiceImpl implements BreederService {

    @Autowired
    private BreederRepository breederRepo;

    @Autowired
    private ImageRepository imageRepo;

    @Override
    public List<Breeder> findAll() {
        return breederRepo.findAll();
    }

    @Override
    public BreederDto findById(String id) {
        return breederRepo.findById(id)
                .map(breederMapperInstance::breederToBreederDto).map(b->{
                    imageRepo.findDPImageHavingBreederId(id)
                            .ifPresent(b::setProfilePicture);
                    return b;
                })
                .orElseThrow(() -> new RuntimeException ("** Breeder not found for id :: " + id));
    }

    public Breeder findByPetId(String id){
        return breederRepo.findPetId(id);
    }

    public List<Image> findImagesByBreederId(String id){
        return imageRepo.findImagesHavingBreederId(id);
    }
    @Override
    public Breeder save(Breeder breeder) {
        return breederRepo.save(breeder);

    }



    @Override
    public Breeder update(String id, Breeder Breeder) {
    	breederRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Breeder not found for id :: " + id));
        
    	Breeder.setId(id);
    	return breederRepo.save(Breeder);
    }

    @Override
    public void delete(String id) {
        breederRepo.findById(id).ifPresent(Breeder -> breederRepo.delete(Breeder));
    }
}
