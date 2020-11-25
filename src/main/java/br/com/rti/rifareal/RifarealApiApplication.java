package br.com.rti.rifareal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class RifarealApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RifarealApiApplication.class, args);
	}

}
