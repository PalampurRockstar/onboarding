package com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.table.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetDto {
    private String id;
    private String breed;
    private String title;
    @JsonProperty("type_code")
    private String typeCode;
    private String type;
    private String image;
    private String gender;
    private String description;
    private String dob;
    private int rating;
    private Price price;
    private Breeder breeder;
    private Location location;
    private Image profilePicture;
    private List<Review> reviews=new ArrayList<>();
    private List<Document> documents=new ArrayList<>();
    private List<Image> images= new ArrayList<>();
}