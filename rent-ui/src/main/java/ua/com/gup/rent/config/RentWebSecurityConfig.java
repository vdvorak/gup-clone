package ua.com.gup.rent.config;

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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.com.gup.rent.repository.security.SecurityContextRepositoryImpl;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableOAuth2Sso
public class RentWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment e;
//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
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

                .antMatchers(HttpMethod.GET, "/api/**")
                .permitAll()

                .anyRequest()
                .authenticated();
    }

//    @Bean(name="springSecurityFilterChain")
//    public FilterChainProxy getFilterChainProxy() throws ServletException, Exception {
//        List listOfFilterChains = new ArrayList();
//
//        listOfFilterChains.add(new DefaultSecurityFilterChain(
//                new AntPathRequestMatcher("/**"),
//                securityContextPersistenceFilter()));
//        return new FilterChainProxy(listOfFilterChains);
//    }

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

//    @Bean
//    @Primary
//    public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
//        return new SecurityContextPersistenceFilter(
//                new SecurityContextRepositoryImpl());
//    }

//    @Bean
//    public UserAuthenticationConverter userAuthenticationConverter() {
//        DefaultUserAuthenticationConverter converter =
//                new DefaultUserAuthenticationConverter();
//        converter.setUserDetailsService(userDetailsService);
//        return converter;
//    }
//
//    @Bean
//    @Primary
//    public AccessTokenConverter accessTokenConverter() {
//        DefaultAccessTokenConverter datc
//                = new DefaultAccessTokenConverter();
//        datc.setUserTokenConverter(userAuthenticationConverter());
//        return datc;
//    }
//
//    @Bean
//    @Primary
//    public RemoteTokenServices getPreProdRemoteTokenServices() {
//        RemoteTokenServices rts = new RemoteTokenServices();
//        rts.setCheckTokenEndpointUrl(e.getRequiredProperty("security.oauth2.client.check-token"));
//        rts.setClientId(e.getRequiredProperty("security.oauth2.client.client-id"));
//        rts.setClientSecret(e.getRequiredProperty("security.oauth2.client.client-secret"));
//
//        rts.setAccessTokenConverter(accessTokenConverter());
//
//        return rts;
//    }
}
