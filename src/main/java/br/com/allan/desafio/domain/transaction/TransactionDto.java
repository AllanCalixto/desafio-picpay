package br.com.allan.desafio.domain.transaction;

import java.math.BigDecimal;

public record TransactionDto(
		BigDecimal amount,
		Long payerId,
		Long payeeId
		) {

}
