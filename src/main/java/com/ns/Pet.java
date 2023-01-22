package com.ns;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Pet {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	public Pet() {

	}
	public Pet(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

	}



}
