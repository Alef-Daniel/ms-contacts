package br.com.picpay.mscontacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
public class MsContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsContactsApplication.class, args);
	}

}
