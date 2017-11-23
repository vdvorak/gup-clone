package ua.com.gup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.com.gup.config.security.GupOauth2UserAuthenticationConverter;

import java.util.Arrays;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private Environment e;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST)
                .authenticated()

                .antMatchers(HttpMethod.PUT)
                .authenticated()

                .antMatchers(HttpMethod.DELETE)
                .authenticated()

                .antMatchers(HttpMethod.GET, "/api/users/authenticate")
                .authenticated()

                .antMatchers(HttpMethod.GET, "/api/**")
                .permitAll()

                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    @Profile("dev")
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedHeaders(Arrays.asList("*", "http://localhost"));
        config.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public UserAuthenticationConverter userAuthenticationConverter () {
        DefaultUserAuthenticationConverter converter =
                new DefaultUserAuthenticationConverter();
        converter.setUserDetailsService(userDetailsService);
        return converter;
    }

    @Bean
    @Primary
    public AccessTokenConverter accessTokenConverter() {
        DefaultAccessTokenConverter datc
                = new DefaultAccessTokenConverter();
        datc.setUserTokenConverter(userAuthenticationConverter());
        return datc;
    }

    @Bean
    @Primary
    public RemoteTokenServices getPreProdRemoteTokenServices () {
        RemoteTokenServices rts = new RemoteTokenServices();
        rts.setCheckTokenEndpointUrl(e.getRequiredProperty("security.oauth2.client.check-token"));
        rts.setClientId(e.getRequiredProperty("security.oauth2.client.client-id"));
        rts.setClientSecret(e.getRequiredProperty("security.oauth2.client.client-secret"));

        rts.setAccessTokenConverter(accessTokenConverter());

        return rts;
    }

//    private Filter ssoFilter() {
//        OAuth2ClientAuthenticationProcessingFilter filter = new GupOAuth2ClientAuthenticationProcessingFilter("/api/users/authenticate");
//
//        AuthorizationCodeResourceDetails details = oAuth2ProtectedResourceDetails();
//        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(details, oauth2ClientContext);
//                filter.setRestTemplate(oAuth2RestTemplate);
//
//        ResourceServerProperties userInfoUri = resourceServerProperties();
//        UserInfoTokenServices tokenServices = new UserInfoTokenServices("https://oauth.dev.gup.ua/oauth/api/user/principal",
//                oAuth2ProtectedResourceDetails().getClientId());
//
//        tokenServices.setRestTemplate(oAuth2RestTemplate);
//        filter.setTokenServices(tokenServices);
//        return filter;
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "gup.oauth2.client")
//    public AuthorizationCodeResourceDetails oAuth2ProtectedResourceDetails() {
//        AuthorizationCodeResourceDetails authorizationCodeResourceDetails = new AuthorizationCodeResourceDetails();
//                return authorizationCodeResourceDetails;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "gup.oauth2.resource")
//    public ResourceServerProperties resourceServerProperties() {
//        ResourceServerProperties resourceServerProperties = new ResourceServerProperties();
//        return resourceServerProperties;
//    }
//
//    @Bean
//    public FilterRegistrationBean oauth2ClientFilterRegistration(
//            OAuth2ClientContextFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(filter);
//        registration.setOrder(-100);
//        return registration;
//    }


}
