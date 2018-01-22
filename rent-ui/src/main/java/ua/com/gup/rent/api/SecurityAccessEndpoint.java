package ua.com.gup.rent.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.common.web.api.AbstractSecurityAccessEndpoint;

@RestController
@RequestMapping("/api/security")
public class SecurityAccessEndpoint  extends AbstractSecurityAccessEndpoint{
}
