package ua.com.gup.rent.config;

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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
import ua.com.gup.common.security.CommonUserAuthenticationConverter;
import ua.com.gup.common.security.filter.CsrfTokenRequestBindingFilter;
import ua.com.gup.common.security.filter.CsrfTokenResponseHeaderBindingFilter;
import ua.com.gup.rent.security.RentLogoutSuccessHandler;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class RentWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment e;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
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
                .authenticated()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .logoutUrl("/api/users/logout");

        http.addFilterBefore(new CsrfTokenRequestBindingFilter(), CsrfFilter.class);
        http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new RentLogoutSuccessHandler(HttpStatus.OK);
    }

    @Bean
    @Profile("dev")
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    @Bean
    public UserAuthenticationConverter userAuthenticationConverter() {
        CommonUserAuthenticationConverter converter =
                new CommonUserAuthenticationConverter();
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
    public RemoteTokenServices getPreProdRemoteTokenServices() {
        RemoteTokenServices rts = new RemoteTokenServices();
        rts.setCheckTokenEndpointUrl(e.getRequiredProperty("security.oauth2.client.checkTokenUri"));
        rts.setClientId(e.getRequiredProperty("security.oauth2.client.clientId"));
        rts.setClientSecret(e.getRequiredProperty("security.oauth2.client.clientSecret"));
        rts.setAccessTokenConverter(accessTokenConverter());
        return rts;
    }
}
