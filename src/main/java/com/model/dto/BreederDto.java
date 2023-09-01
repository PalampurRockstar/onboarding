package com.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.model.table.Image;
import com.model.table.Location;
import com.model.table.Pet;
import com.model.table.Review;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BreederDto {
	private String id;
	public String name;
	public String code;
	public Integer rating;
	private List<Pet> pets= new ArrayList<>();
	private Location location;
	private List<Review> reviews;
	private List<Image> images= new ArrayList<>();
	private Image profilePicture;
}
