package br.com.erudio.springjavaerudio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// implementação da configuração da extenção via query param/query string
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // pega as definições de cors para cofigurar
    @Value("${cors.originPatterns:default}")
    private String corsOriginPatters = "";

    // libera as urls que estão no application.yml
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatters.split(",");
        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT") poderia ser especificado quais verbos
                .allowedMethods("*")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);
    }
}
