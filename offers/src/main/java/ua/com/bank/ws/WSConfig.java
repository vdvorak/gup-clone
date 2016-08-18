package ua.com.bank.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("ua.com.bank.ws.wsdl");
		return marshaller;
	}

	@Bean
	public WSWebService wsInviteCode(Jaxb2Marshaller marshaller) {
		WSWebService ws = new WSWebService();
        ws.setDefaultUri("http://localhost:8081/invitecodes.wsdl");
        ws.setMarshaller(marshaller);
        ws.setUnmarshaller(marshaller);
		return ws;
	}

}
