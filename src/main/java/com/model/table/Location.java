package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="locations")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Location {

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	public String longitude;
	public String latitude;

	@JsonBackReference(value="to-pet")
	@OneToOne(mappedBy = "location")
	private Pet pet;
	@JsonBackReference
	@OneToOne(mappedBy = "location" ,cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private Breeder breeder;
//	@JsonManagedReference
	@OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

}
