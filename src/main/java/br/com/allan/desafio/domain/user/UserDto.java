package br.com.allan.desafio.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserDto(
		@NotBlank
		String name,

		@NotBlank
		@Email

		@NotBlank
		String document, 
		BigDecimal balance, 
		String email,
		String login,
		String password,
		UserType userType
		) {

}
