package ua.com.gup.rent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class RentWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers()
                    .frameOptions().disable()
                .and()
//                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST)
                //.authenticated()
                .permitAll()

                .antMatchers(HttpMethod.PUT)
                .authenticated()
                .antMatchers(HttpMethod.GET, "/api/rents")
                .authenticated()

                .antMatchers(HttpMethod.GET, "/api/calendar/**")
                .permitAll();

//                .anyRequest()
//                .authenticated();

    }

}
