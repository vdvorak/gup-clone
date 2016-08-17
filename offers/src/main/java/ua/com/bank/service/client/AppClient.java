package com.bank.service.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.bank.service.wsdl.*;

public class AppClient extends WebServiceGatewaySupport  {
	public GetInviteCodeResponse getInviteCode(int id) {
        GetInviteCodeRequest req = new GetInviteCodeRequest();
        req.setId(id);
        GetInviteCodeResponse resp = (GetInviteCodeResponse) getWebServiceTemplate().marshalSendAndReceive(
                req, new SoapActionCallback("http://localhost:8080/soap/bank/getInviteCodeResponse"));
		return resp;
	}
}
