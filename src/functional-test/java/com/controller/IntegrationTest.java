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
        Image expectedImage1= createImage();

        //trigger
        ResponseEntity<Image> responseImage1 = restTemplate.postForEntity("/image", expectedImage1,Image.class);
        Image actualImage1= responseImage1.getBody();

        //verify
        assertThat(responseImage1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualImage1.getId());

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
        expectedPet.getImages().add(actualImage1);
        expectedPet.setLocation(actualLocation);
        //trigger
        ResponseEntity<Pet> responsePet = restTemplate.postForEntity("/pet", expectedPet,Pet.class);
        Pet actualPet= responsePet.getBody();

        //verify
        assertThat(responsePet.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualPet.getId());

        //when
        Image expectedImage2= createImage();
        expectedImage2.setPet(actualPet);
        //trigger
        ResponseEntity<Image> responseImage2 = restTemplate.postForEntity("/image", expectedImage2,Image.class);
        Image actualImage2= responseImage2.getBody();

        //verify
        assertThat(responseImage2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualImage2.getId());



        //when
        Review expectedReview2= createReview();
        expectedReview2.setPet(actualPet);
        //trigger
        ResponseEntity<Review> responseReview2 = restTemplate.postForEntity("/review", expectedReview2,Review.class);
        Review actualReview2= responseReview2.getBody();

        //verify
        assertThat(responseReview2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualReview2.getId());

    }

    private Breeder createBreeder() throws JsonProcessingException {
        return mapper.readValue("{\"name\":\"Sourabh\",\"code\":\"B001\",\"rating\":3}", Breeder.class);
    }
    private Location createLocation() throws JsonProcessingException {
        return mapper.readValue("{\"name\":\"Chiang mai\",\"longitude\":\"111111\",\"latitude\":\"222222\"}", Location.class);
    }
    private Address createAddress() throws JsonProcessingException {
        return mapper.readValue("{\"street\":\"Some street\",\"pin\":\"176064\"}", Address.class);
    }
    private Pet createPet() throws JsonProcessingException {
        return mapper.readValue("{" +
                "\"breed\": \"Beagle\"," +
                "\"title\": \"Adorable Silver Chinchilla Persian\"," +
                "  \"type_code\": \"\"," +
                "  \"type\": \"cat\"," +
                "  \"image\": \"\"," +
                "  \"gender\": \"male\"," +
                "  \"rating\": 3," +
                "  \"dob\": \"2023-01-28T16:44:03.520Z\",\n" +
                "  \"description\":\"Lorem ipsum dolor sit ame\"}", Pet.class);
    }
    private Image createImage() throws JsonProcessingException {
        return mapper.readValue("{\"path\":\"some path\",\"file\":\"image1.jpg\",\"cdn\":\"host1\"}", Image.class);
    }
    private Document createDocument() throws JsonProcessingException {
        return mapper.readValue("{\"name\":\"Vaccination certificate\",\"validUntil\":\"2023-01-28T16:44:03.520Z\",\"documentNumber\":\"34343\"}", Document.class);
    }

    private Review createReview() throws JsonProcessingException {
        return mapper.readValue("{\n" +
                "    \"comment\": \"Breeder has good experience\",\n" +
                "    \"likeCount\": 2,\n" +
                "    \"disLikeCount\": 3\n" +
                "}", Review.class);
    }
}
