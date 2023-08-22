package com.controller;

import com.AppStart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.table.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import java.util.Collections;

import static graphql.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppStart.class)
@TestPropertySource(locations = "classpath:application.yaml")
@ActiveProfiles("local")
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ConfigurableApplicationContext context;

    @BeforeEach
    public void before() {
        ConfigurableEnvironment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("Active Profiles: " + String.join(", ", activeProfiles));
        System.out.println("Properties from application.yml:");
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
                if (propertySource instanceof org.springframework.core.env.EnumerablePropertySource) {
                    org.springframework.core.env.EnumerablePropertySource<?> enumerablePropertySource =
                            (org.springframework.core.env.EnumerablePropertySource<?>) propertySource;
                    for (String propertyName : enumerablePropertySource.getPropertyNames()) {
                        Object propertyValue = enumerablePropertySource.getProperty(propertyName);
                        System.out.println("  " + propertyName + ": " + propertyValue);
                    }
                }
        }
    }

    @Test
    public void shouldTestIntegration() throws JsonProcessingException {
        //when
        Address expectedAddress= createAddress();

        //trigger
        ResponseEntity<Address> addressResponse = restTemplate.postForEntity("/address", expectedAddress,Address.class);
        Address actualAddress= addressResponse.getBody();

        //verify
        assertThat(addressResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualAddress.getId());

        //when
        Location expectedLocation= createLocation();
        expectedLocation.setAddress(actualAddress);

        //trigger
        ResponseEntity<Location> responseLocation = restTemplate.postForEntity("/location", expectedLocation,Location.class);
        Location actualLocation= responseLocation.getBody();

        //verify
        assertThat(responseLocation.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualLocation.getId());


        //when
        Breeder expectedBreeder= createBreeder();
        expectedBreeder.setLocation(actualLocation);

        //trigger
        ResponseEntity<Breeder> responseBreeder = restTemplate.postForEntity("/breeder", expectedBreeder,Breeder.class);
        Breeder actualBreeder= responseBreeder.getBody();

        //verify
        assertThat(responseBreeder.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualBreeder.getId());

        //when
        Image expectedImage= createImage();

        //trigger
        ResponseEntity<Image> responseImage = restTemplate.postForEntity("/image", expectedImage,Image.class);
        Image actualImage= responseImage.getBody();

        //verify
        assertThat(responseImage.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualImage.getId());

        //when
        Document expectedDocument= createDocument();

        //trigger
        ResponseEntity<Document> responseDocument = restTemplate.postForEntity("/document", expectedDocument,Document.class);
        Document actualDocument= responseDocument.getBody();

        //verify
        assertThat(responseDocument.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualDocument.getId());

        //when
        Pet expectedPet= createPet();
        expectedPet.setBreeder(actualBreeder);
        expectedPet.getDocuments().add(actualDocument);
        expectedPet.setImages( Collections.singletonList(actualImage));
        //trigger
        ResponseEntity<Pet> responsePet = restTemplate.postForEntity("/pet", expectedPet,Pet.class);
        Pet actualPet= responsePet.getBody();

        //verify
        assertThat(responsePet.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualPet.getId());

    }

    private Breeder createBreeder() throws JsonProcessingException {
        return mapper.readValue("{\"name\":\"Sourabh\",\"code\":\"B001\",\"rating\":3}", Breeder.class);
    }
    private Location createLocation() throws JsonProcessingException {
        return mapper.readValue("{\"name\":\"Chiang mai\",\"longitude\":\"\",\"latitude\":\"\"}", Location.class);
    }
    private Address createAddress() throws JsonProcessingException {
        return mapper.readValue("{\"street\":\"\",\"pin\":\"\"}", Address.class);
    }
    private Pet createPet() throws JsonProcessingException {
        return mapper.readValue("{\"breed\":\"lab\",\"title\":\"Bruno\",\"type_code\":\"\",\"type\":\"dog\",\"image\":\"\",\"gender\":\"male\",\"rating\":3,\"dob\":\"2023-01-28T16:44:03.520Z\",\"description\":\"Lorem \"}", Pet.class);
    }
    private Image createImage() throws JsonProcessingException {
        return mapper.readValue("{\"path\":\"some/path\",\"file\":\"image1.jpg\",\"cdn\":\"host1\"}", Image.class);
    }
    private Document createDocument() throws JsonProcessingException {
        return mapper.readValue("{\"name\":\"Vaccination certificate\",\"validUntil\":\"2023-01-28T16:44:03.520Z\",\"documentNumber\":\"34343\"}", Document.class);
    }
}
