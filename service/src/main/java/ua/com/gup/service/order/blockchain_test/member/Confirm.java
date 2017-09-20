package ua.com.gup.service.order.blockchain_test.member;

import okhttp3.Response;

import java.io.IOException;


public interface Confirm extends Unavailable {

    Response confirm() throws IOException;

}
