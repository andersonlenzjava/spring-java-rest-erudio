package br.com.erudio.springjavaerudio.services;

import br.com.erudio.springjavaerudio.data.vo.v1.security.AccountCredentialsVO;
import br.com.erudio.springjavaerudio.data.vo.v1.security.TokenVO;
import br.com.erudio.springjavaerudio.repository.UserRepository;
import br.com.erudio.springjavaerudio.security.Jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// serviço de autenticacao
@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            // pega as credenciais
            var username = data.getUsername();
            var password = data.getPassword();
            // tenta fazer a autenticação
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);

            var tokenResponse = new TokenVO();

            if (user != null) {
                // getRoles foge da lógica do JPA foi implementado

                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                // dá para fazer logger aqui disto
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }

            return (ResponseEntity) ResponseEntity.ok(tokenResponse);

        } catch (Exception e) {
            // se não der certo ele cai fora
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

}
