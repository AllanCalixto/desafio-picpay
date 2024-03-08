package br.com.allan.desafio.domain.notification;

import org.springframework.stereotype.Service;

import br.com.allan.desafio.domain.user.User;

@Service
public class NotificationService {

	public void sendNotification(User user, String message) {
		String email = user.getEmail();
		
		System.out.println(email + message);
	}
}
