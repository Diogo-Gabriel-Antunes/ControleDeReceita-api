package br.com.ControleDeGastos.controller;

import br.com.ControleDeGastos.DTO.tokenDto;
import br.com.ControleDeGastos.Model.LoginForm;
import br.com.ControleDeGastos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authenticate = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);

            return ResponseEntity.ok(new tokenDto(token, "Bearer"));
        }catch (AuthenticationException e ){
            return ResponseEntity.badRequest().build();
        }

    }
}
