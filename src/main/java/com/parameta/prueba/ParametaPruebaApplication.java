package com.parameta.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = { "com.Parameta.prueba.service", "com.parameta.prueba.feing" })
public class ParametaPruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParametaPruebaApplication.class, args);
	}

}
