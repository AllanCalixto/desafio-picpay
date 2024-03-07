package br.com.allan.desafio.controller;

import br.com.allan.desafio.domain.user.AuthDto;
import br.com.allan.desafio.domain.user.User;
import br.com.allan.desafio.infra.security.TokenJWTDto;
import br.com.allan.desafio.infra.security.TokenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody AuthDto dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDto(tokenJWT));
    }
}
