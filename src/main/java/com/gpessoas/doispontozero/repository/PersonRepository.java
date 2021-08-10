package com.gpessoas.doispontozero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpessoas.doispontozero.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
