package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.config.PetIdGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pets")
public class Pet {
	@Id
	@GeneratedValue(generator = PetIdGenerator.GENERATOR_NAME)
	@GenericGenerator(
			name = PetIdGenerator.GENERATOR_NAME,
			strategy = "com.config.PetIdGenerator",
			parameters = {@Parameter(name = PetIdGenerator.PREFIX_PARAM, value = "PET_")})
	private String id;
	private String breed;
	private String title;
	@Column(name = "type_code")
	@JsonProperty("type_code")
	private String typeCode;
	private String type;
	private String image;
	private String gender;
	private String description;
	private String dob;
	private int rating;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "price_id", referencedColumnName = "id")
	private Price price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "breeder_id")
	@JsonIgnoreProperties("pets")
	private Breeder breeder;
	@JsonManagedReference(value="to-pet")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

//	@JsonManagedReference(value="review-pet")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "pet_id")
//	private List<Review> reviews;


	@JsonManagedReference(value="document-pet")
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "pet_id")
	private List<Document> documents=new ArrayList<>();

	@JsonManagedReference(value="image-pet")
	@OneToMany(mappedBy = "pet",cascade = CascadeType.MERGE,  orphanRemoval = true)

	private List<Image> images;
}
