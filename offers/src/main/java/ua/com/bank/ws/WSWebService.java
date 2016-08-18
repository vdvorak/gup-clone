package ua.com.bank.ws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import ua.com.bank.ws.wsdl.*;

public class WSWebService extends WebServiceGatewaySupport {

	public GetInviteCodeResponse getInviteCodeResponse(int id) {
        GetInviteCodeRequest req = new GetInviteCodeRequest();
        req.setId(id);

        GetInviteCodeResponse resp = (GetInviteCodeResponse) getWebServiceTemplate().marshalSendAndReceive(req, new SoapActionCallback("http://localhost:8081/getInviteCodeResponse"));
		return resp;
	}

}
