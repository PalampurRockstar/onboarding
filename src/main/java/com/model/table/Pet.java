package com.model.table;

import com.fasterxml.jackson.annotation.*;
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
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "price_id", referencedColumnName = "id")
	private Price price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "breeder_id")
	@JsonIgnoreProperties("pets")
	private Breeder breeder;
	@JsonManagedReference(value="to-pet")
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE,fetch =FetchType.LAZY )
	@JoinColumn(name = "pet_id")
	private List<Review> reviews=new ArrayList<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name = "pet_id")
	private List<Document> documents=new ArrayList<>();

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinColumn(name = "pet_id")
	private List<Image> images= new ArrayList<>();
}
