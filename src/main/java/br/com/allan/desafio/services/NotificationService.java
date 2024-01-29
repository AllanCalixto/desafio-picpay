package br.com.allan.desafio.services;

import org.springframework.stereotype.Service;

import br.com.allan.desafio.domain.user.User;

@Service
public class NotificationService {

	public void sendNotification(User user, String message) {
		String email = user.getEmail();
		
		System.out.println(email + message);
	}
}
