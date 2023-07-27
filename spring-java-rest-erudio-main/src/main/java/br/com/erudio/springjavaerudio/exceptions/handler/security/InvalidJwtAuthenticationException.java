package br.com.erudio.springjavaerudio.exceptions.handler.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

// atenção por que tem de ser do spring security core
@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 1L;
    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
