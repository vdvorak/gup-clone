package ua.com.gup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
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
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//import ua.com.gup.config.security.GupOauth2UserAuthenticationConverter;

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
                .csrf().disable()
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
                
                .antMatchers(HttpMethod.DELETE)
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
    public UserAuthenticationConverter userAuthenticationConverter() {
        UserAuthenticationConverter converter = new SalesUserAuthenticationConverter();
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
