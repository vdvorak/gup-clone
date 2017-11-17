package ua.com.gup.rent.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class RentWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .headers()
//                    .frameOptions().disable()
//                .and()
//                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST)
                .authenticated()

                .antMatchers(HttpMethod.PUT)
                .authenticated()
                .antMatchers(HttpMethod.GET, "/api/rents")
                .authenticated()

                .antMatchers(HttpMethod.GET, "/api/calendar/**")
                .permitAll()
                .anyRequest()
                .authenticated();

    }

}
