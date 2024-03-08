package br.com.allan.desafio.domain.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.allan.desafio.domain.user.User;
import br.com.allan.desafio.domain.user.UserDto;
import br.com.allan.desafio.domain.user.UserType;
import br.com.allan.desafio.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public void saveUser(User user) {
		this.repository.save(user);
	}
	
	public User createUser(UserDto user) {
		User newUser = new User(user);
		this.saveUser(newUser);
		return newUser;
	}

	public List<User> getAllUsers() {
		return this.repository.findAll();
	}
	
	public User findById(Long id) throws Exception {
		return this.repository.findById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
	}

	public boolean validateUser(User payer, BigDecimal amount) throws Exception {
		if(payer.getUserType() == UserType.MERCHANT) {
			throw new Exception("Um usuario lojista não pode realizar trasações");
		}
		if(payer.getBalance().compareTo(amount)< 0) {
			throw new Exception("Saldo insuficiente");
		}
		return true;
	}

}
