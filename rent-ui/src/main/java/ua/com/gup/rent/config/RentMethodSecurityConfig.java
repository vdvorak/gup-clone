package ua.com.gup.rent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import ua.com.gup.rent.security.RentPermissionEvaluator;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, jsr250Enabled = true, securedEnabled = true)
public class RentMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new RentPermissionEvaluator());
        return expressionHandler;
    }
}
