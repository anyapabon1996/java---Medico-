package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    //A ESTE ENDPOINT PODRÁN ENTRAR SOLO LAS PERSONA CON UN TOKEN
    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }

    //solo las personas con el role de employee pueden entrar
    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYEE')")
    @GetMapping("/first")
    public String first(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }

    //solo las personas con role de admin puede entrar
    @PreAuthorize("hasAuthority('SCOPE_ROLE_MANAGER')")
    @GetMapping("/second")
    public String second(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }

}
