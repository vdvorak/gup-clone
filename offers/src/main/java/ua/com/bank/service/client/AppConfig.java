package com.bank.service.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AppConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.bank.service.wsdl");
		return marshaller;
	}

	@Bean
	public AppClient studentClient(Jaxb2Marshaller marshaller) {
		AppClient client = new AppClient();
		client.setDefaultUri("http://localhost:8080/ws/bank/students.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
