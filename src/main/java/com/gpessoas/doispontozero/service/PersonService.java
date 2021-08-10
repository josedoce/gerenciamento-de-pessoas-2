package com.gpessoas.doispontozero.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpessoas.doispontozero.dto.request.PersonDTO;
import com.gpessoas.doispontozero.dto.response.MessageResponseDTO;
import com.gpessoas.doispontozero.entity.Person;
import com.gpessoas.doispontozero.exception.PersonNotFoundException;
import com.gpessoas.doispontozero.mapper.PersonMapper;
import com.gpessoas.doispontozero.mapper.PersonMapperImp;
import com.gpessoas.doispontozero.repository.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonService {
	
	private PersonMapper personMapper;
	
	private PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository, PersonMapperImp personMapper) {
		this.personMapper = personMapper;
		this.personRepository = personRepository;
	}
	
	public PersonDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		Person savedPerson = personRepository.save(personToSave);
		return personMapper.toDTO(savedPerson);
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		personRepository.deleteById(person.getId());;
	}

	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		Person personToUpdate = personMapper.toModel(personDTO);
		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Edited person with ID");
	}
	
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(()-> new PersonNotFoundException(id));
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message+" "+id)
				.build();
	}
}