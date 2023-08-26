package com.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.BreederRepository;
import com.repository.PetRepository;
import com.model.table.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static graphql.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@DataJpaTest
class PetServiceImplTestBackup {
    @Autowired
    private PetRepository petRepo;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
    @Test
    void testFindAll() throws JsonProcessingException {
        ObjectMapper om=new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Pet pet=om.readValue("{\"breed\":\"lab\",\"title\":\"Bruno\",\"type\":\"dog\",\"image\":\"\",\"gender\":\"male\",\"rating\":3,\"dob\":\"2023-01-28T16:44:03.520Z\",\"description\":\"Lorem ipsum dolor sit amet consectetur. Feugiat eget ullamcorper aliquam lorem odio et cras ut vivamus. Non malesuada scelerisque sociis aliquam elit eget aliquam nam.\",\"documents\":[{\"name\":\"Vaccination certificate\",\"validUntil\":\"2023-01-28T16:44:03.520Z\",\"documentNumber\":\"34343\"},{\"name\":\"Health certificate\",\"validUntil\":\"2023-01-28T16:44:03.520Z\",\"documentNumber\":\"123333\"},{\"name\":\"Birth certificate\",\"validUntil\":\"2023-01-29T16:44:03.520Z\",\"documentNumber\":\"123333\"}],\"price\":{\"amount\":2011,\"currencyCode\":\"INR\"},\"breeder\":{\"name\":\"Sourabh\",\"code\":\"B001\",\"location\":{\"name\":\"Chiang mai\",\"longitude\":\"\",\"latitude\":\"\",\"address\":{\"street\":\"\",\"pin\":\"\"}},\"rating\":3,\"reviews\":[{\"comment\":\"Pet is clean\",\"likeCount\":2,\"disLikeCount\":3,\"evidence\":[{\"path\":\"some/path\",\"file\":\"image1.jpg\",\"cdn\":\"host1\"}]},{\"comment\":\"All documents are prvided\",\"likeCount\":2,\"disLikeCount\":3,\"evidence\":[{\"path\":\"some/path\",\"file\":\"image1.jpg\",\"cdn\":\"host1\"}]}]},\"location\":{\"name\":\"Chaing mai\",\"longitude\":\"\",\"latitude\":\"\",\"address\":{\"street\":\"\",\"pin\":\"\"}},\"reviews\":[{\"comment\":\"Hi\",\"likeCount\":1,\"disLikeCount\":2,\"evidence\":[{\"path\":\"some/path\",\"file\":\"image1.jpg\",\"cdn\":\"host1\"}]}],\"images\":[{\"path\":\"some/path\",\"file\":\"image1.jpg\",\"cdn\":\"host1\"},{\"path\":\"some/path\",\"file\":\"image2.jpg\",\"cdn\":\"host2\"}]}",Pet.class);
        pet=petRepo.save(pet);
        assertNotNull(pet.getId());
    }

}