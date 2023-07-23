package br.com.erudio.springjavaerudio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// implementação da configuração da extenção via query param/query string
@Configuration
public class WebConfig implements WebMvcConfigurer {
}
