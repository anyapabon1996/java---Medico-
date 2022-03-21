package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//HACEMOS LA SEGURIDAD POR METODOS
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MethodSecurityConfiguration
        extends GlobalMethodSecurityConfiguration {
}