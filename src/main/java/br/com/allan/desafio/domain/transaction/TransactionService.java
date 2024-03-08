package br.com.allan.desafio.domain.transaction;

import java.time.LocalDateTime;
import java.util.Map;

import br.com.allan.desafio.domain.user.UserService;
import br.com.allan.desafio.domain.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.allan.desafio.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired	
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private NotificationService notificationService;
	
	@Transactional
	public Transaction createTransaction(TransactionDto transaction) throws Exception {
		
		var payer = this.userService.findById(transaction.payerId());
		var payee = this.userService.findById(transaction.payeeId());
		
		this.userService.validateUser(payer, transaction.amount());
		
		boolean isAuthorize = authorizeTransaction();
		
		if(!isAuthorize) {
			throw new Exception("Transação não autorizada");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.amount());
		newTransaction.setPayer(payer);
		newTransaction.setPayee(payee);
		newTransaction.setTransactionTime(LocalDateTime.now());
		
		payer.setBalance(payer.getBalance().subtract(transaction.amount()));
		payee.setBalance(payee.getBalance().add(transaction.amount()));
		
		this.repository.save(newTransaction);
		this.userService.saveUser(payer);
		this.userService.saveUser(payee);
		
		notificationService.sendNotification(payer, "Transacao realizada com sucesso");
		notificationService.sendNotification(payee, "Transacao recebida com sucesso");
		
		return newTransaction;
		
		
		
	}
	
	
	public boolean authorizeTransaction() {
		var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			String message = (String) response.getBody().get("message");
			return"Autorizado".equalsIgnoreCase(message);
			
		}else return false;
	}

}
