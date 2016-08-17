package com.bank.service.client;

import com.bank.service.wsdl.GetInviteCodeResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppClientTest {


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * test SOAP-WS
     */
    @Test
    public void testSoapWS() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	    ctx.register(AppConfig.class);
	    ctx.refresh();

		AppClient client = ctx.getBean(AppClient.class);
        GetInviteCodeResponse resp = null;

        resp = client.getInviteCode(1);
		System.err.println("\n    ID: 1");
		System.err.println("  Code: " + resp.getInviteCode().getCode());
		System.err.println("Amount: " + resp.getInviteCode().getAmount());
		System.err.println("UserId: " + resp.getInviteCode().getUserId());

        resp = client.getInviteCode(2);
        System.err.println("\n    ID: 2");
        System.err.println("  Code: " + resp.getInviteCode().getCode());
        System.err.println("Amount: " + resp.getInviteCode().getAmount());
        System.err.println("UserId: " + resp.getInviteCode().getUserId());
	}

}
