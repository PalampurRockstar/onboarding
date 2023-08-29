package com.model.mapper;


import com.model.dto.PetDto;
import com.model.table.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PetMapper {
    PetMapper petMapperInstance = Mappers.getMapper(PetMapper.class);

    PetDto petToPetDto(Pet pet);
    Pet petDtoToPet(PetDto petDto);
}