package br.com.erudio.springjavaerudio.config;

import br.com.erudio.springjavaerudio.security.Jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;


// classe que está descontinuada o que está implementado aqui
// classe que tinha lá no curso da alura
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());

        DelegatingPasswordEncoder passwordEncoder =
                new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        return passwordEncoder;
    }



}
