package br.com.erudio.springjavaerudio.security.Jwt;

import br.com.erudio.springjavaerudio.data.vo.v1.security.TokenVO;
import br.com.erudio.springjavaerudio.exceptions.handler.security.InvalidJwtAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

// classe que gerência o token
@Service
public class JwtTokenProvider {

    // pega as definições de cors para cofigurar // que vem do application.yml
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    // é um inicializador que inicia antes das outras instânciaçoes (autowirder)
    // permitindo não gerar problemas de falta de instância
    // tipo um berofe all dos testes automatizados
    @PostConstruct
    protected void init() {
        // cria a criptografica da secret key atribuida no application.yml
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    // coloca a validade da data do token
    public TokenVO createAccessToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);
        // cria o objeto tokenVO depois das validações, já gerando um novo token e seus dados
        return new TokenVO(username, true, now, validity, accessToken, refreshToken);
    }


    // métodos geradores do token
    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        // pega a url do servidor
        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }

    // classe que faz a autenticação do token, manipula com o HMC E A SECRET DO APP.YML PARA VER AS CREDENTIALS
    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // decodifica com o algortimo gerado
    private DecodedJWT decodedToken(String token) {
        // outro nome para algoritimo, pq em cima já tem um
        Algorithm algoritimo = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(algoritimo).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT; // tipo um json
    }

    // pegar os cabecalhos da requisicao
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        // é o token que da requisição automática/ ou manual, é preciso descartar o bearer
        // Bearer TOKEN

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // pega somente o restante do token
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    // método para validar o token em relação a data
    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if (decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT Token!");
        }

    }



}
