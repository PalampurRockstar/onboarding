package com.model.table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.config.PetIdGenerator;

import javax.persistence.*;

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
	private int rating;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "price_id", referencedColumnName = "id")
	private Price price;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "breeder_id", referencedColumnName = "id")
	private Breeder breeder;
	@JsonManagedReference(value="to-pet")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;
}
