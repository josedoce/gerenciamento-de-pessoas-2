package com.gpessoas.doispontozero.mapper;


import org.mapstruct.Mapper;

import com.gpessoas.doispontozero.dto.request.PersonDTO;
import com.gpessoas.doispontozero.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

	Person toModel(PersonDTO personDTO);
	
	PersonDTO toDTO(Person person);
	
}
