package ua.com.itproekt.gup.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class GupResponseErrorHandler extends DefaultResponseErrorHandler {

    protected boolean hasError(HttpStatus statusCode) {
        if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR)) return false;
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR || statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }

}
