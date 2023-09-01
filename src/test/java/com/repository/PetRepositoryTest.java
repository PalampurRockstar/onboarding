package com.repository;

import com.model.dto.PetDto;
import com.model.table.Pet;
import com.service.impl.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PetRepositoryTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private BreederRepository breederRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testFindAll() {
//        // Mocking
//        when(petRepository.findAll()).thenReturn(Collections.singletonList(new Pet()));
//
//        // Test
//        List<Pet> pets = petService.findAll();
//
//        // Verify
//        assertEquals(1, pets.size());
//        verify(petRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testFindById() {
//        // Mocking
//        when(petRepository.findById("123")).thenReturn(Optional.of(new Pet()));
//
//        // Test
//        PetDto pet = petService.findById("123");
//
//        // Verify
////        assertEquals("123", pet.getId());
//        verify(petRepository, times(1)).findById("123");
//    }

    // Write similar tests for other methods like save, search, update, and delete
    // ...

}