package com.model.mapper;


import com.model.dto.BreederDto;
import com.model.dto.PetDto;
import com.model.table.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BreederMapper {
    BreederMapper breederMapperInstance = Mappers.getMapper(BreederMapper.class);
//    @Mapping(target = "id",source = "id")
    BreederDto breederToBreederDto(Breeder breeder);
    Breeder breederDtoToBreeder(BreederDto breeder);

    public default BreederDto breederToBreederDtoWithGetterSetter(Breeder breeder) {
        BreederDto breederDto = new BreederDto();
        breederDto.setId(breeder.getId());
        breederDto.setName(breeder.getName());
        breederDto.setCode(breeder.getCode());
        breederDto.setRating(breeder.getRating());
        breederDto.setPets(breeder.getPets());
        breederDto.setLocation(breeder.getLocation());
        breederDto.setReviews(breeder.getReviews());
        breederDto.setImages(breeder.getImages());
        return breederDto;
    }
    public static Breeder breederDtoToBreederWithGetterSetter(BreederDto breederDto) {
        Breeder breeder = new Breeder();
        breeder.setId(breederDto.getId());
        breeder.setName(breederDto.getName());
        breeder.setCode(breederDto.getCode());
        breeder.setRating(breederDto.getRating());
        breeder.setPets(breederDto.getPets());
        breeder.setLocation(breederDto.getLocation());
        breeder.setReviews(breederDto.getReviews());
        breeder.setImages(breederDto.getImages());
        return breeder;
    }
}