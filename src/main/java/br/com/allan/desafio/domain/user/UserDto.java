package br.com.allan.desafio.domain.user;

import java.math.BigDecimal;

public record UserDto(
		String name,
		String document, 
		BigDecimal balance, 
		String email,
		String login,
		String password,
		UserType userType
		) {

}
