package com.ns;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetAppApiApplication.class, args);
	}

	@Bean
	ApplicationRunner init(PetRepository petRepository) {
		return (ApplicationArguments args) -> dataSetup(petRepository);
	}

	private void dataSetup(PetRepository petRepository) {
		System.out.println("Adding pet");
		Pet pet = new Pet("Niraj","Sonawane");
		petRepository.save(pet);
	}
}
