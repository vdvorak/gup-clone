package ua.com.itproekt.gup.server.api.rest.filestorage;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class FileStorageRestControllerTest {

    private Logger logger = Logger.getLogger(FileStorageRestControllerTest.class);
    private RestTemplate restTemplate;
    private final String pathPhoto = "png_32x32.png";
    private final String uriUploadPhoto = "http://localhost:9090/api/rest/fileStorage/profile/photo/upload?cacheImage=true";

    @Before
    public void setUp() {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory( HttpClients.createDefault() );
        restTemplate = new RestTemplate( requestFactory );
    }

    @After
    public void tearDown() {
        restTemplate = null;
    }

    /**
     * test Photo-Upload Status
     */
    @Test(timeout = 1000)
    public void testPhotoUploadStatus() {
        try {
            String[] actual = photoUpload(pathPhoto, uriUploadPhoto);
            assertThat(actual[0].toString(), equalTo(HttpStatus.CREATED));
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
    }

    /**
     * test Photo-Upload Response
     */
    @Test(timeout = 1000)
    public void testPhotoUploadResponse() {
        String expected = "{\n  \"id\": \"57ab66514c8ef6b11c9d6faa\"\n}";

        try {
            String[] actual = photoUpload(pathPhoto, uriUploadPhoto);
            assertThat(actual[1].toString(), equalTo(expected));
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
    private String[] photoUpload(String path, String uri) throws FileNotFoundException, ConnectException, RuntimeException, HttpMediaTypeNotAcceptableException, MissingServletRequestParameterException {
        MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<>();
        multipartMap.add("name", path);
        multipartMap.add("file", new ClassPathResource(path));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<Object> request = new HttpEntity<Object>(multipartMap, headers);
        logger.info("Created multipart request: " + multipartMap);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> httpResponse = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
            logger.warn("Posting request: " + uri);

            if( httpResponse.getStatusCode().equals(HttpStatus.CREATED) ) {
                logger.warn(httpResponse);
                logger.warn(httpResponse.getStatusCode());
                logger.warn(httpResponse.getHeaders().getContentLength());
                logger.warn(httpResponse.getBody());
                return new String[]{httpResponse.getStatusCode().toString(), httpResponse.getBody()};
            } else {
                logger.error("Problems with the request. Http status: " + httpResponse.getStatusCode());
                return new String[]{httpResponse.getStatusCode().toString(), "Problems with the request. Http status: " + httpResponse.getStatusCode()};
            }
        } catch (Exception e){
            if(e.getMessage().contains("java.io.FileNotFoundException")) throw new FileNotFoundException( e.getMessage() );
            if(e.getMessage().contains("java.net.ConnectException")) throw new ConnectException( e.getMessage() );
            if(e.getMessage().contains("Internal Server Error")) throw new RuntimeException( e.getMessage() );
            if(e.getMessage().contains("Not Acceptable")) throw new HttpMediaTypeNotAcceptableException( e.getMessage() );
            if(e.getMessage().contains("Bad Request")) throw new MissingServletRequestParameterException( e.getMessage(),e.getMessage() );
            return new String[]{null, e.getLocalizedMessage()};
        }
    }

}
