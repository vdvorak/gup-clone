package ua.com.bank.ws;

import ua.com.bank.ws.wsdl.GetInviteCodeResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WSWebServiceTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * test (SOAP) WS
     */
    @Test
    public void testService() {
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//	    ctx.register(WSConfig.class);
//	    ctx.refresh();
//
//		WSWebService service = ctx.getBean(WSWebService.class);
//        GetInviteCodeResponse resp = null;
//
//        resp = service.getInviteCodeResponse(1);
//		System.err.println("\n    ID: 1");
//		System.err.println("  Code: " + resp.getInviteCode().getCode());
//		System.err.println("Amount: " + resp.getInviteCode().getAmount());
//		System.err.println("UserId: " + resp.getInviteCode().getUserId());
//
//        resp = service.getInviteCodeResponse(2);
//        System.err.println("\n    ID: 2");
//        System.err.println("  Code: " + resp.getInviteCode().getCode());
//        System.err.println("Amount: " + resp.getInviteCode().getAmount());
//        System.err.println("UserId: " + resp.getInviteCode().getUserId());
	}

}
