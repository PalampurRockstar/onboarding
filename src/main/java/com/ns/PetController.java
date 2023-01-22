package com.ns;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class PetController {


    private final PetRepository petRepository;

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets(@RequestParam(required = false) String firstName) {
        try {
            List<Pet> pets = new ArrayList<Pet>();

            if (firstName == null)
                pets.addAll(petRepository.findAll());
            else
                petRepository.findByFirstNameContaining(firstName)
                        .forEach(pets::add);

            if (pets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") String id) {
        Optional<Pet> petData = petRepository.findById(id);
        if (petData.isPresent()) {
            return new ResponseEntity<>(petData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        try {
            Pet _pet = petRepository.save(new Pet(pet.getFirstName(), pet.getLastName()));
            return new ResponseEntity<>(_pet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") String id, @RequestBody Pet pet) {
        Optional<Pet> petData = petRepository.findById(id);

        if (petData.isPresent()) {
            Pet _pet = petData.get();
            _pet.setFirstName(pet.getFirstName());
            _pet.setLastName(pet.getLastName());
            return new ResponseEntity<>(petRepository.save(_pet), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<HttpStatus> deletePet(@PathVariable("id") String id) {
        try {
            petRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/pets")
    public ResponseEntity<HttpStatus> deleteAllPets() {
        try {
            petRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
