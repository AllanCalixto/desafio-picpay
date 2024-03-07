package br.com.allan.desafio.controller;

import br.com.allan.desafio.domain.user.AuthDto;
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

    @PostMapping
    public ResponseEntity login(@RequestBody AuthDto dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
