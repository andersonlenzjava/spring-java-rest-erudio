package br.com.erudio.springjavaerudio.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResurceNotFoundExceptionException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ResurceNotFoundExceptionException(String ex) {
        super(ex);
    }
}
