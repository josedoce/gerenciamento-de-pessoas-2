package com.gpessoas.doispontozero.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gpessoas.doispontozero.dto.request.PersonDTO;
import com.gpessoas.doispontozero.dto.response.MessageResponseDTO;
import com.gpessoas.doispontozero.exception.PersonNotFoundException;
import com.gpessoas.doispontozero.service.PersonService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "https://gerenciamento-de-pessoa.herokuapp.com",allowCredentials = "true")
@RestController
@RequestMapping("/api/v2/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
	private PersonService personService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PersonDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return this.personService.createPerson(personDTO);
	}
	
	@GetMapping
	public List<PersonDTO> listAll(){
		return this.personService.listAll();
	}
	
	@GetMapping("/{id}")
	public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
		return personService.findById(id);
	}
	
	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
		return personService.updateById(id, personDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}
	
}
