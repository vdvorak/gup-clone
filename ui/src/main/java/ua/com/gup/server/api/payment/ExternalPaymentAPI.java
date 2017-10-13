package ua.com.gup.server.api.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.server.dto.BalanceDTO;
import ua.com.gup.util.security.SecurityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Api for Payment.
 */
@RestController
@RequestMapping("/api/payment")
@PropertySource("classpath:properties/payment.properties")
public class ExternalPaymentAPI {
    private final Logger log = LoggerFactory.getLogger(ExternalPaymentAPI.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payment.url.host}")
    private String host;
    @Value("${payment.url.user.addBalance}")
    private String addBalance;
    @Value("${payment.url.user.getBalance}")
    private String getBalance;
    @Value("${payment.url.user.historyBalance}")
    private String historyBalance;


    @CrossOrigin
    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/admin/addBalance", method = RequestMethod.POST,
                                                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity putBalanceUserFormAdmin(@RequestBody BalanceDTO balanceDTO) {
        //check access
        if (SecurityUtils.getCurrentUserId() == null || SecurityUtils.getCurrentUserId().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN)||!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_MODERATOR)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        //url
        String url = host + addBalance;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        //Query parameters
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", SecurityUtils.getCurrentUserId());
        params.put("amount", balanceDTO.getAmount());

        HttpEntity<Map<String,Object>> requestBody = new HttpEntity<>(params);

        ResponseEntity responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestBody, Object.class);
        log.info("response {}", responseEntity);
        return new ResponseEntity(responseEntity.getBody(), HttpStatus.OK);
    }

    @CrossOrigin
    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-balance/balance", method = RequestMethod.GET,
                                                        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserBalance() {
        //check access
        if (SecurityUtils.getCurrentUserId() == null || SecurityUtils.getCurrentUserId().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        //url
        String url = host + getBalance;
        //Query parameters
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", SecurityUtils.getCurrentUserId());

        UriComponentsBuilder builder = addQueryParameterInHttpMetodGet(url, params);

        ResponseEntity responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Object.class);
        log.info("response {}", responseEntity);
        return new ResponseEntity(responseEntity.getBody(), HttpStatus.OK);
    }

    @CrossOrigin
    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-balance/history", method = RequestMethod.GET,
                                                        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkHistoryBalance(@RequestParam Integer limit,
                                              @RequestParam(required = false) Integer offset) {
        //check access
        if (SecurityUtils.getCurrentUserId() == null || SecurityUtils.getCurrentUserId().isEmpty()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        //url
        String url = host + historyBalance;
        //Query parameters
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", SecurityUtils.getCurrentUserId());
        params.put("limit", limit);

        if (offset != null) {
            params.put("offset", offset);
        }

        UriComponentsBuilder builder = addQueryParameterInHttpMetodGet(url, params);

        ResponseEntity responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Object.class);
        log.info("response {}", responseEntity);
        return new ResponseEntity(responseEntity.getBody(), HttpStatus.OK);
    }

    private UriComponentsBuilder addQueryParameterInHttpMetodGet(String url, Map<String, Object> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        params.forEach((key, value) -> builder.queryParam(key, value));
        return builder;
    }

}
