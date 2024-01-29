package br.com.allan.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allan.desafio.domain.transaction.Transaction;
import br.com.allan.desafio.domain.transaction.TransactionDto;
import br.com.allan.desafio.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transaction) throws Exception{
		var newTransaction = this.transactionService.createTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
	}

}
