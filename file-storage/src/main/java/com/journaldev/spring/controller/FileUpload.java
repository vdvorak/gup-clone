package com.journaldev.spring.controller;

import com.journaldev.spring.CreatedObjResp;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.apache.http.impl.client.HttpClients;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;

public class FileUpload {

    private Logger             logger = Logger.getLogger(FileUpload.class);
    private RestTemplate restTemplate;

    public FileUpload() {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory( HttpClients.createDefault() );
        restTemplate = new RestTemplate( requestFactory );
    }

    /**
     * Upload single file using Spring Controller
     * ******************************************
     * 200 OK («хорошо»)
     * 201 Created («создано»)
     * 400 Bad Request («плохой, неверный запрос»)
     * 406 Not Acceptable («неприемлемо»)
     *
     * @param path
     * @param uri
     * @return
     * @throws FileNotFoundException
     * @throws ConnectException
     * @throws RuntimeException
     * @throws HttpMediaTypeNotAcceptableException
     * @throws MissingServletRequestParameterException
     */
    CreatedObjResp photoUpload(String path, String uri) throws FileNotFoundException, ConnectException, RuntimeException, HttpMediaTypeNotAcceptableException, MissingServletRequestParameterException {
        MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String, Object>();
        multipartMap.add("name", path);
        multipartMap.add("file", new ClassPathResource(path));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); //headers.setContentType(new MediaType("multipart", "form-data"));
        HttpEntity<Object> request = new HttpEntity<Object>(multipartMap, headers);
        logger.info("Created multipart request: " + multipartMap);

        CreatedObjResp resp = new CreatedObjResp();
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<CreatedObjResp> respEntity = restTemplate.exchange(uri, HttpMethod.POST, request, CreatedObjResp.class);
            logger.warn("Posting request: " + uri);

            if( respEntity.getStatusCode().equals(HttpStatus.CREATED) ) {
                logger.warn(respEntity);
                logger.warn(respEntity.getStatusCode());
//                logger.warn(respEntity.getHeaders().getContentLength());
                logger.warn(respEntity.getBody().getId());
                resp = respEntity.getBody();
            } else {
                logger.error("Problems with the request. Http status: " + respEntity.getStatusCode());
            }
        } catch (Exception e){
            if(e.getMessage().contains("java.io.FileNotFoundException")) throw new FileNotFoundException( e.getMessage() );
            if(e.getMessage().contains("java.net.ConnectException")) throw new ConnectException( e.getMessage() );
            if(e.getMessage().contains("Internal Server Error")) throw new RuntimeException( e.getMessage() );
            if(e.getMessage().contains("Not Acceptable")) throw new HttpMediaTypeNotAcceptableException( e.getMessage() );
            if(e.getMessage().contains("Bad Request")) throw new MissingServletRequestParameterException( e.getMessage(),e.getMessage() );
//            System.err.println(e.getLocalizedMessage());
        }
        return resp;
    }

}
