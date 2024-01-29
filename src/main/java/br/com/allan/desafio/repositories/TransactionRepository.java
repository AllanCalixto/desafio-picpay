package br.com.allan.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.allan.desafio.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
