package ua.com.gup.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.com.gup.listener.authentication.OAuthAuthenticationFailureHandler;
import ua.com.gup.listener.authentication.OAuthAuthenticationSuccessHandler;
import ua.com.gup.listener.authentication.OAuthLogoutSuccessHandler;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ClientResources facebook = facebook();
        http
                .authorizeRequests()
                .antMatchers("/api/oauth/authorize").authenticated()
                .antMatchers("/",
                        "/login**",
                        "/register",
                        "/register/confirm",
                        "/register/password/restore",
                        "/register/password/reset",
                        "/api/users/exists/**")
                .permitAll()
                .antMatchers("/logout").authenticated()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(oAuthLogoutSuccessHandler())

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())

                .and().addFilterBefore(ssoFilter(facebook, "/login/facebook", facebookRestTemplate(facebook)), BasicAuthenticationFilter.class);
    }

    @Bean("facebookRestTemplate")
    public OAuth2RestTemplate facebookRestTemplate(ClientResources client) {

        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        template.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                logger.debug("requestURI: ", request.getURI());
                logger.debug("requestBody: ", new String(body));
                return execution.execute(request, body);
            }
        }));
        return template;
    }


    public Filter ssoFilter(ClientResources client, String path, OAuth2RestTemplate template) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        filter.setAuthenticationSuccessHandler(facebookAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(facebookAuthenticationFailureHandler());
        return filter;
    }

    @Bean
    public FacebookAuthenticationSuccessHandler facebookAuthenticationSuccessHandler() {
        return new FacebookAuthenticationSuccessHandler();
    }


    @Bean
    public FacebookAuthenticationFailureHandler facebookAuthenticationFailureHandler() {
        return new FacebookAuthenticationFailureHandler();
    }


    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }


    @Bean
    public OAuthLogoutSuccessHandler oAuthLogoutSuccessHandler() {
        return new OAuthLogoutSuccessHandler();

    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new OAuthAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new OAuthAuthenticationFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


}
