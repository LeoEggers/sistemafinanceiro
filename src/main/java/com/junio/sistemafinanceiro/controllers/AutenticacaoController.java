package com.junio.sistemafinanceiro.controllers;

import com.sistemafinanceiro.infra.DadosTokenJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public DadosTokenJWT autenticar(@RequestBody DadosLogin dadosLogin) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dadosLogin.login(), dadosLogin.senha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return new DadosTokenJWT(tokenJWT);
    }
}
