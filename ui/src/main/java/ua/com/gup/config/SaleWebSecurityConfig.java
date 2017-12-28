package ua.com.gup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.com.gup.common.security.filter.CsrfTokenRequestBindingFilter;
import ua.com.gup.common.security.filter.CsrfTokenResponseHeaderBindingFilter;
import ua.com.gup.server.security.SaleLogoutSuccessHandler;

import java.util.Arrays;


@Configuration
@EnableOAuth2Sso
public class SaleWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private Environment e;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST)
                .permitAll()
                
                .antMatchers(HttpMethod.PUT)
                .authenticated()

                .antMatchers(HttpMethod.DELETE)
                .authenticated()

                .antMatchers(HttpMethod.GET, "/api/users/authenticate")
                .authenticated()

                .antMatchers(HttpMethod.GET, "/api/**")
                .permitAll()    
                
                .anyRequest()
                .authenticated()

                .and()
                .logout()
                .logoutUrl("/api/users/logout")
                .logoutSuccessHandler(logoutSuccessHandler());


        http.addFilterBefore(new CsrfTokenRequestBindingFilter(), CsrfFilter.class);
        http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new SaleLogoutSuccessHandler(HttpStatus.OK);
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
    public UserAuthenticationConverter userAuthenticationConverter() {
        UserAuthenticationConverter converter = new SaleUserAuthenticationConverter();
        return converter;
    }

    @Bean
    @Primary
    public AccessTokenConverter accessTokenConverter() {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter());
        return accessTokenConverter;
    }


    @Bean
    @Primary
    public RemoteTokenServices getPreProdRemoteTokenServices() {
        RemoteTokenServices rts = new RemoteTokenServices();
        rts.setCheckTokenEndpointUrl(e.getRequiredProperty("security.oauth2.client.check-token"));
        rts.setClientId(e.getRequiredProperty("security.oauth2.client.client-id"));
        rts.setClientSecret(e.getRequiredProperty("security.oauth2.client.client-secret"));
        rts.setAccessTokenConverter(accessTokenConverter());
        return rts;
    }
}
