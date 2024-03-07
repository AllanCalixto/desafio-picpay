package br.com.allan.desafio.infra.security;

import br.com.allan.desafio.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API PIC-PAY")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(dateExpired())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Ero ao gerar token jwt!", exception);
        }
    }

    private Instant dateExpired() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}