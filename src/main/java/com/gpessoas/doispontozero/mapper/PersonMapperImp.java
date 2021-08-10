package com.gpessoas.doispontozero.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gpessoas.doispontozero.dto.request.PersonDTO;
import com.gpessoas.doispontozero.dto.request.PhoneDTO;
import com.gpessoas.doispontozero.entity.Person;
import com.gpessoas.doispontozero.entity.Phone;

@Component
public class PersonMapperImp implements PersonMapper {
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	@Override
	public Person toModel(PersonDTO personDTO) {
		if(personDTO == null) {
			return null;
		}
		
		LocalDate data = LocalDate.parse(personDTO.getBirthDate(), formato);
		
		Person person = new Person();
		Phone number = null;
		
		person.setId(personDTO.getId());
		person.setFirstName(personDTO.getFirstName());
		person.setLastName(personDTO.getLastName());
		person.setCpf(personDTO.getCpf());
		person.setBirthDate(data);
		List<Phone> phones = new ArrayList<>(personDTO.getPhones().size());
		
		for(PhoneDTO phone : personDTO.getPhones()) {
			number =  new Phone();
			number.setId(phone.getId());
			number.setType(phone.getType());
			number.setNumber(phone.getNumber());
			phones.add(number);
			number = null;
		}
		
		person.setPhones(phones);
		return person;
	}

	@Override
	public PersonDTO toDTO(Person person) {
		if(person == null) {
			return null;
		}
		PersonDTO personDTO = new PersonDTO();
		PhoneDTO number = null;
		personDTO.setId(person.getId());
		personDTO.setFirstName(person.getFirstName());
		personDTO.setLastName(person.getLastName());
		personDTO.setCpf(person.getCpf());
		personDTO.setBirthDate(formato.format(person.getBirthDate()));
		List<PhoneDTO> phones = new ArrayList<>();
		
		for(Phone phone : person.getPhones()) {
			number = new PhoneDTO();
			
			number.setId(phone.getId());
			number.setType(phone.getType());
			number.setNumber(phone.getNumber());
			phones.add(number);
			number = null;
		}
		personDTO.setPhones(phones);
		
		return personDTO;
	}
	
}