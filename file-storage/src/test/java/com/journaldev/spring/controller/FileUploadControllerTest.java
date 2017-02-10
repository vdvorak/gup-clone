package com.journaldev.spring.controller;

import com.journaldev.spring.App;
import com.journaldev.spring.CreatedObjResp;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest("server.port:9000")
public class FileUploadControllerTest {

    private Logger     logger = Logger.getLogger(FileUploadControllerTest.class);
    private FileUpload upload;
    private String urlPhotoDownload;
    private String urlPhotoUpload;

    @Before
    public void setUp() {
        upload = new FileUpload();
        urlPhotoDownload = "https://www.google.com/images/srpr";
        urlPhotoUpload = "http://localhost:9000/fileStorage/photo/upload?cacheImage=true";
    }

    @After
    public void tearDown() {
        upload = null;
    }

    /**
     * test Photo-Download Status
     */
    @Test(timeout = 5000)
    public void testPhotoDownloadStatus() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        HttpHeaders       headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String actual_logo11w = "logo11w.png";

        RestTemplate restTemplate = new RestTemplate(messageConverters);
        ResponseEntity<byte[]> actual_logo = restTemplate.exchange(urlPhotoDownload + "/" + actual_logo11w, HttpMethod.GET, entity, byte[].class);

        System.err.println( "testPhotoDownloadStatus: " + actual_logo.getHeaders() );

        assertThat(actual_logo.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(actual_logo.getHeaders().get("Content-Length").toString(), equalTo("[12775]"));
    }

    /**
     * test Upload-File Response
     */
    @Test
    public void testPhotoUploadResponse() {
        CreatedObjResp actual_614x461 = null;
        CreatedObjResp actual_32x32 = null;
        CreatedObjResp actual_83x100 = null;
        CreatedObjResp actual_145x217 = null;
        CreatedObjResp actual_240x50 = null;
        CreatedObjResp actual2_32x32 = null;

        try {
//            actual_614x461 = upload.photoUpload("png_614x461.png", urlPhotoUpload);
//            System.out.println( "testPhotoUploadStatus: " + actual_32x32.getId() );                    // Error WEB-SERVICE: 500 Internal Server Error
            actual_32x32 = upload.photoUpload("png_32x32.png", urlPhotoUpload);
//            System.err.println( "testPhotoUploadStatus: " + actual_32x32.getId() );                      // { "id" : "png_32x32.png" }
            actual_83x100 = upload.photoUpload("png_83x100.png", urlPhotoUpload);
//            System.err.println( "testPhotoUploadStatus: " + actual_83x100.getId() );                     // { "id" : "png_83x100.png" }
            actual_145x217 = upload.photoUpload("png_145x217.jpg", urlPhotoUpload);
//            System.err.println( "testPhotoUploadStatus: " + actual_145x217.getId() );                    // { "id" : "png_145x217.png" }
            actual_240x50 = upload.photoUpload("png_240x50.png", urlPhotoUpload);
//            System.err.println( "testPhotoUploadStatus: " + actual_240x50.getId() );                     // { "id" : "png_240x50.png" }
//            actual2_32x32 = upload.photoUpload("png_32x32.png", "http://93.73.109.38:9000/api/rest/fileStorage/profile/photo/upload?cacheImage=true");
//            System.err.println( "testPhotoUploadStatus: " + actual2_32x32.getId() );                     // Exception BAD-REQUEST: Required 400 Bad Request parameter '400 Bad Request' is not present
        } catch (FileNotFoundException e){
            logger.fatal("Unavailable FILE: " + e.getMessage());
        } catch (ConnectException e){
            logger.fatal("Unavailable URL: " + e.getMessage());
        } catch (HttpMediaTypeNotAcceptableException e){
            logger.warn("Exception NOT ACCEPTABLE: " + e.getMessage());
        } catch (MissingServletRequestParameterException e){
            logger.error("Exception BAD-REQUEST: " + e.getMessage());
        } catch (RuntimeException e){
            logger.error("Error WEB-SERVICE: " + e.getMessage());
        }

        assertThat(actual_32x32.getId(), equalTo("png_32x32.png"));
        assertThat(actual_83x100.getId(), equalTo("png_83x100.png"));
        assertThat(actual_145x217.getId(), equalTo("png_145x217.jpg"));
        assertThat(actual_240x50.getId(), equalTo("png_240x50.png"));
    }

}
