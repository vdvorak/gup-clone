package ua.com.gup.oauth2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.http.converter.jaxb.JaxbOAuth2ExceptionMessageConverter;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class GupOAuth2ExceptionRenderer implements OAuth2ExceptionRenderer {
    private final Log logger = LogFactory.getLog(GupOAuth2ExceptionRenderer.class);
    private List<HttpMessageConverter<?>> messageConverters = this.geDefaultMessageConverters();

    public GupOAuth2ExceptionRenderer() {
    }

    public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }
    @Override
    public void handleHttpEntityResponse(HttpEntity<?> responseEntity, ServletWebRequest webRequest) throws Exception {
        if (responseEntity != null) {
            HttpInputMessage inputMessage = this.createHttpInputMessage(webRequest);
            HttpOutputMessage outputMessage = this.createHttpOutputMessage(webRequest);
            if (responseEntity instanceof ResponseEntity && outputMessage instanceof ServerHttpResponse) {
                ((ServerHttpResponse)outputMessage).setStatusCode(((ResponseEntity)responseEntity).getStatusCode());
            }

            HttpHeaders entityHeaders = responseEntity.getHeaders();
            if (!entityHeaders.isEmpty()) {
                outputMessage.getHeaders().putAll(entityHeaders);
            }

            Object body = responseEntity.getBody();
            if (body != null) {
                this.writeWithMessageConverters(body, inputMessage, outputMessage);
            } else {
                outputMessage.getBody();
            }

        }
    }

    private void writeWithMessageConverters(Object returnValue, HttpInputMessage inputMessage, HttpOutputMessage outputMessage) throws IOException, HttpMediaTypeNotAcceptableException {
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }

        MediaType.sortByQualityValue(acceptedMediaTypes);
        Class<?> returnValueType = returnValue.getClass();
        List<MediaType> allSupportedMediaTypes = new ArrayList();
        Iterator var7 = acceptedMediaTypes.iterator();

        while(var7.hasNext()) {
            MediaType acceptedMediaType = (MediaType)var7.next();
            Iterator var9 = this.messageConverters.iterator();

            while(var9.hasNext()) {
                HttpMessageConverter messageConverter = (HttpMessageConverter)var9.next();
                if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
                    messageConverter.write(returnValue, acceptedMediaType, outputMessage);
                    if (this.logger.isDebugEnabled()) {
                        MediaType contentType = outputMessage.getHeaders().getContentType();
                        if (contentType == null) {
                            contentType = acceptedMediaType;
                        }

                        this.logger.debug("Written [" + returnValue + "] as \"" + contentType + "\" using [" + messageConverter + "]");
                    }

                    return;
                }
            }
        }

        var7 = this.messageConverters.iterator();

        while(var7.hasNext()) {
            HttpMessageConverter messageConverter = (HttpMessageConverter)var7.next();
            allSupportedMediaTypes.addAll(messageConverter.getSupportedMediaTypes());
        }

        throw new HttpMediaTypeNotAcceptableException(allSupportedMediaTypes);
    }

    private List<HttpMessageConverter<?>> geDefaultMessageConverters() {
        List<HttpMessageConverter<?>> result = new ArrayList();
        result.addAll((new RestTemplate()).getMessageConverters());
        result.add(new JaxbOAuth2ExceptionMessageConverter());
        return result;
    }

    private HttpInputMessage createHttpInputMessage(NativeWebRequest webRequest) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest)webRequest.getNativeRequest(HttpServletRequest.class);
        return new ServletServerHttpRequest(servletRequest);
    }

    private HttpOutputMessage createHttpOutputMessage(NativeWebRequest webRequest) throws Exception {
        HttpServletResponse servletResponse = (HttpServletResponse)webRequest.getNativeResponse();
        return new ServletServerHttpResponse(servletResponse);
    }
}
