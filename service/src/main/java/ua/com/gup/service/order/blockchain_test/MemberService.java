package ua.com.gup.service.order.blockchain_test;

import okhttp3.Response;
import ua.com.gup.service.order.blockchain_test.member.Available;

import java.io.IOException;


public class MemberService implements Available {
    private TransactionService service;

    private MemberService() {
    }
    public MemberService(TransactionService service) {
        this.service = service;
    }

    public TransactionService getTransaction() {
        return service;
    }

    @Override
    public Response confirm()
            throws IOException {
        return service.confirm();
    }

    @Override
    public Response reject()
            throws IOException {
        return service.reject();
    }
}
