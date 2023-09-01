package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity
@Table(name="breeders")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Breeder {

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private String code;
	private Integer rating;

	@JsonIgnoreProperties("breeder")
	@OneToMany(mappedBy = "breeder", orphanRemoval = true, cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private List<Pet> pets= new ArrayList<>();


	@JsonManagedReference
	@OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE,fetch =FetchType.LAZY )
	@JoinColumn(name = "breeder_id")
	private List<Review> reviews;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinColumn(name = "breeder_id")
	private List<Image> images= new ArrayList<>();
}
