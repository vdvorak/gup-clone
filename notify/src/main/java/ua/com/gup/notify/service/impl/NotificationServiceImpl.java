package ua.com.gup.notify.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.notify.model.Notification;
import ua.com.gup.notify.service.NotificationService;

import javax.annotation.PostConstruct;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    private Environment e;
    @Autowired
    private RestTemplate restTemplate;
    private UriComponentsBuilder uriComponentsBuilder;

    @PostConstruct
    public void initialize() {
        uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme(e.getRequiredProperty("gup.notifications-api.scheme"))
                .host(e.getRequiredProperty("gup.notifications-api.host")).port(e.getRequiredProperty("gup.notifications-api.port"));
    }

    @Override
    public void sendNotification(Notification notification) {
        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/notify").build();
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(uriComponents.toUri(), notification, Void.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            log.info("notification successfully sent");
        }
    }
}
