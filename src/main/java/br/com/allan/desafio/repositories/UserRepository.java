package br.com.allan.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.allan.desafio.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);
}
