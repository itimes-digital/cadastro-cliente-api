package com.builders.cad.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.builders.cad.cliente.repository"})
@EntityScan(basePackages = "com.builders.cad.cliente.model")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.builders.cad.cliente.*", basePackageClasses = CadastroClienteApiApplication.class)
public class CadastroClienteApiApplication extends SpringBootServletInitializer{

	public CadastroClienteApiApplication() {
	    super();
	    setRegisterErrorPageFilter(false);
	}

	public static void main(String[] args) {
		SpringApplication.run(CadastroClienteApiApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CadastroClienteApiApplication.class);
	}
	
}


