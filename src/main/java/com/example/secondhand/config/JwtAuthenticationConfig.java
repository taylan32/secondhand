package com.example.secondhand.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@EnableWebSecurity
@Configuration
public class JwtAuthenticationConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.mvcMatchers("/",
                                "/v1/home/version")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                        .oauth2ResourceServer().jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri)));
    }


}
