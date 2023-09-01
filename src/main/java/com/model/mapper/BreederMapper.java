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
    @Mapping(target = "id",source = "id")
    BreederDto breederToBreederDto(Breeder breeder);
    Breeder breederDtoToBreeder(BreederDto breeder);
}