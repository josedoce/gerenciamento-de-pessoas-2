package com.gpessoas.doispontozero.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	
	private Long id;
	
	@NotEmpty(message = "O primeiro nome precisa ser preenchido.")
	@Size(min = 2, max = 100)
	private String firstName;
	
	@NotEmpty(message = "O segundo nome precisa ser preenchido.")
	@Size(min=2, max=100)
	private String lastName;
	
	@NotEmpty(message = "O cpf precisa ser preenchido.")
	@Size(min=11, max=14)
	private String cpf;
	
	private String birthDate;
	
	@Valid
	@NotEmpty(message = "É necessario pelo menos um numero para contato.")
	private List<PhoneDTO> phones;
	
}
