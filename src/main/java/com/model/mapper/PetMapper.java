package com.model.mapper;


import com.model.dto.PetDto;
import com.model.table.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PetMapper {
    PetMapper petMapperInstance = Mappers.getMapper(PetMapper.class);


    PetDto petToPetDto(Pet pet);


    Pet petDtoToPet(PetDto petDto);


    public default PetDto petToPetDtoWithGetterSetter(Pet pet) {
        if ( pet == null ) {
            return null;
        }

        PetDto petDto = new PetDto();

        petDto.setBreed( pet.getBreed() );
        petDto.setId( pet.getId() );
        petDto.setTitle( pet.getTitle() );
        petDto.setTypeCode( pet.getTypeCode() );
        petDto.setType( pet.getType() );
        petDto.setImage( pet.getImage() );
        petDto.setGender( pet.getGender() );
        petDto.setDescription( pet.getDescription() );
        petDto.setDob( pet.getDob() );
        petDto.setRating( pet.getRating() );
        petDto.setPrice( pet.getPrice() );
        petDto.setBreeder( pet.getBreeder() );
        petDto.setLocation( pet.getLocation() );
        List<Review> list = pet.getReviews();
        if ( list != null ) {
            petDto.setReviews( new ArrayList<Review>( list ) );
        }
        List<Document> list1 = pet.getDocuments();
        if ( list1 != null ) {
            petDto.setDocuments( new ArrayList<Document>( list1 ) );
        }
        List<Image> list2 = pet.getImages();
        if ( list2 != null ) {
            petDto.setImages( new ArrayList<Image>( list2 ) );
        }

        return petDto;
    }


    public default Pet petDtoToPetWithGEtterSetter(PetDto petDto) {
        if ( petDto == null ) {
            return null;
        }

        Pet.PetBuilder pet = Pet.builder();

        pet.breed( petDto.getBreed() );
        pet.id( petDto.getId() );
        pet.title( petDto.getTitle() );
        pet.typeCode( petDto.getTypeCode() );
        pet.type( petDto.getType() );
        pet.image( petDto.getImage() );
        pet.gender( petDto.getGender() );
        pet.description( petDto.getDescription() );
        pet.dob( petDto.getDob() );
        pet.rating( petDto.getRating() );
        pet.price( petDto.getPrice() );
        pet.breeder( petDto.getBreeder() );
        pet.location( petDto.getLocation() );
        List<Review> list = petDto.getReviews();
        if ( list != null ) {
            pet.reviews( new ArrayList<Review>( list ) );
        }
        List<Document> list1 = petDto.getDocuments();
        if ( list1 != null ) {
            pet.documents( new ArrayList<Document>( list1 ) );
        }
        List<Image> list2 = petDto.getImages();
        if ( list2 != null ) {
            pet.images( new ArrayList<Image>( list2 ) );
        }

        return pet.build();
    }
}