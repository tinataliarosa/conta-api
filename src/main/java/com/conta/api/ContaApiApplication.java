package com.conta.api;

import com.conta.api.entity.Conta;
import com.conta.api.repository.ContaRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class ContaApiApplication {

	@Autowired
	private ContaRepository contaRepository;

	@Bean
	InitializingBean loadData() {
		return () -> {
			contaRepository.save(new Conta(UUID.randomUUID(),"1234","12345","1", BigDecimal.ONE, BigDecimal.TEN));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ContaApiApplication.class, args);
	}


}
