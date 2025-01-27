package br.com.rti.rifareal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Profile( "dev" )
public class WebConfigDev implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping( "/**" ).allowedMethods( "GET", "POST", "PUT", "DELETE", "OPTIONS" ).allowedOrigins( "http://localhost:4200" );
    }
}