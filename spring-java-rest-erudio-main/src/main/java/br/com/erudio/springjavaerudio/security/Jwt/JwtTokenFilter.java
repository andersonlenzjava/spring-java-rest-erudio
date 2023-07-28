package br.com.erudio.springjavaerudio.security.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

// classe filtro geral que intercepta todas as requisições
// servlet inicial
public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // pega o bearer token tratado
        String token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);

        // valida o token
        if (token != null && tokenProvider.validateToken(token))  {

            // se válido retorna uma autenticação
            Authentication auth = tokenProvider.getAuthentication(token);
            if (auth != null) {

                // se válido seta esta autenticação na seção do spring
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
