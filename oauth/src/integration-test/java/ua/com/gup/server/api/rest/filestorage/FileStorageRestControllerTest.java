//package ua.com.itproekt.gup.server.api.rest.filestorage;
//
//import java.io.FileNotFoundException;
//import java.net.ConnectException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.*;
//import org.springframework.http.converter.ByteArrayHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.client.RestTemplate;
//import ua.com.itproekt.gup.util.CreatedObjResp;
//
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//@RunWith(SpringJUnit4ClassRunner.class)
////@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//public class FileStorageRestControllerTest {
//
//    private Logger             logger = Logger.getLogger(FileStorageRestControllerTest.class);
//
//    private final String  uriDownload = "https://www.google.com/images/srpr";
//    private final String pathDownload = "logo11w.png";
//    private final String    uriUpload = "http://93.73.109.38:8083/api/rest/fileStorage/profile/photo/upload";
//    private final String   pathUpload = "png_32x32.png";
//
//    @Before
//    public void setUp() {}
//
//    @After
//    public void tearDown() {}
//
//    /**
//     * test File-Download Status
//     */
//    @Test(timeout = 5000)
//	public void testFileDownloadStatus() {
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//        messageConverters.add(new ByteArrayHttpMessageConverter());
//        HttpHeaders       headers = new HttpHeaders();
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//        RestTemplate restTemplate = new RestTemplate(messageConverters);
//		ResponseEntity<byte[]> actual = restTemplate.exchange(uriDownload + '/' + pathDownload, HttpMethod.GET, entity, byte[].class);
//
//        assertThat("Download-StatusCode", actual.getStatusCode(), equalTo(HttpStatus.OK));
//        assertThat("Download-response", actual.getHeaders().get("Content-Length").toString(), equalTo("[14022]"));
//	}
//
//    /**
//     * ******************************************
//     * 200 OK («хорошо»)
//     * 201 Created («создано»)
//     * 400 Bad Request («плохой, неверный запрос»)
//     * 406 Not Acceptable («неприемлемо»)
//     *
//     * test File-Upload Status
//     */
//    @Test
//    public void testFileUploadStatus() {
//        MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String, Object>();
//        multipartMap.add("name", pathUpload);
//        multipartMap.add("file", new ClassPathResource(pathUpload));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        HttpEntity<Object> request = new HttpEntity<Object>(multipartMap, headers);
//
//        RestTemplate             restTemplate = new RestTemplate();
//        ResponseEntity<CreatedObjResp> actual = restTemplate.exchange(uriUpload, HttpMethod.POST, request, CreatedObjResp.class);
//
//        assertThat("Upload-StatusCode", actual.getStatusCode(), equalTo(HttpStatus.CREATED));
//        assertThat("Upload-response", actual.getBody().getId(), equalTo(pathUpload));
//    }
//
//    /**
//     * ******************************************
//     * 200 OK («хорошо»)
//     * 201 Created («создано»)
//     * 400 Bad Request («плохой, неверный запрос»)
//     * 406 Not Acceptable («неприемлемо»)
//     *
//     * test File-Upload Response
//     */
//    @Test(timeout = 5000)
//    public void testPhotoResponse() {
//        CreatedObjResp actual = null;
//        String       expected = "1209348756767845";
//
//        try {
//            actual = photoUpload("png_32x32.png", "http://localhost:8083/fileStorage/photo/upload?cacheImage=true");
//        } catch (FileNotFoundException e){
//            logger.fatal("Unavailable FILE: " + e.getMessage());
//        } catch (ConnectException e){
//            logger.fatal("Unavailable URL: " + e.getMessage());
//        } catch (HttpMediaTypeNotAcceptableException e){
//            logger.warn("Exception NOT ACCEPTABLE: " + e.getMessage());
//        } catch (MissingServletRequestParameterException e){
//            logger.error("Exception BAD-REQUEST: " + e.getMessage());
//        } catch (RuntimeException e){
//            logger.error("Error WEB-SERVICE: " + e.getMessage());
//        }
//        assertThat("Upload-response", actual.getId(), equalTo(expected));
//    }
//
//
//    /**
//     *
//     * @param path
//     * @param uri
//     * @throws java.io.FileNotFoundException
//     * @throws java.net.ConnectException
//     * @throws RuntimeException
//     * @throws org.springframework.web.HttpMediaTypeNotAcceptableException
//     * @throws org.springframework.web.bind.MissingServletRequestParameterException
//     */
//    CreatedObjResp photoUpload(String path, String uri) throws FileNotFoundException, ConnectException, RuntimeException, HttpMediaTypeNotAcceptableException, MissingServletRequestParameterException {
//        MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String, Object>();
//        multipartMap.add("name", path);
//        multipartMap.add("file", new ClassPathResource(path));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        HttpEntity<Object> request = new HttpEntity<Object>(multipartMap, headers);
//        logger.info("Created multipart request: " + multipartMap);
//
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            ResponseEntity<CreatedObjResp> httpResponse = restTemplate.exchange(uriUpload, HttpMethod.POST, request, CreatedObjResp.class);
//            logger.warn("Posting request: " + uri);
//
//            if (httpResponse.getStatusCode().equals(HttpStatus.CREATED)) {
//                logger.warn(httpResponse);
//                logger.warn(httpResponse.getStatusCode());
//                logger.warn(httpResponse.getHeaders().getContentLength());
//                logger.warn(httpResponse.getBody());
//                return httpResponse.getBody();
//            } else {
//                logger.error("Problems with the request. Http status: " + httpResponse.getStatusCode());
//                return null;
//            }
//        } catch (Exception e) {
//            if (e.getMessage().contains("java.io.FileNotFoundException"))
//                throw new FileNotFoundException(e.getMessage());
//            if (e.getMessage().contains("java.net.ConnectException")) throw new ConnectException(e.getMessage());
//            if (e.getMessage().contains("Internal Server Error")) throw new RuntimeException(e.getMessage());
//            if (e.getMessage().contains("Not Acceptable"))
//                throw new HttpMediaTypeNotAcceptableException(e.getMessage());
//            if (e.getMessage().contains("Bad Request"))
//                throw new MissingServletRequestParameterException(e.getMessage(), e.getMessage());
////            System.err.println(e.getLocalizedMessage());
//            return null;
//        }
//    }
//
//}
