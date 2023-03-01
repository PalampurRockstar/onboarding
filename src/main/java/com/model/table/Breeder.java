package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="breeders")
public class Breeder {

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	public String name;
	public String code;
	public Integer rating;

	@JsonBackReference
	@OneToOne(mappedBy = "breeder")
	private Pet pet;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	@JsonManagedReference(value="review-breeder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "breeder_id")
	private List<Review> reviews;
}
