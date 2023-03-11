package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.config.PetIdGenerator;

import javax.persistence.*;
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
	@JsonBackReference(value="breeder-pet")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "breeder_id")
	private Breeder breeder;
	@JsonManagedReference(value="to-pet")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	@JsonManagedReference(value="review-pet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id")
	private List<Review> reviews;
	@JsonManagedReference(value="document-pet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id")
	private List<Document> documents;

	@JsonManagedReference(value="image-pet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id")
	private List<Image> images;
}
