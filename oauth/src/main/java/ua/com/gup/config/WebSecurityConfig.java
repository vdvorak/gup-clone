package ua.com.gup.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.com.gup.listener.authentication.CustomAuthenticationFailureHandler;
import ua.com.gup.listener.authentication.OAuthAuthenticationSuccessHandler;
import ua.com.gup.listener.authentication.OAuthLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/oauth/authorize").authenticated()
                .antMatchers("/",
                        "/login",
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
                .failureHandler(authenticationFailureHandler())
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(oAuthAuthenticationSuccessHandler());
    }

    @Bean
    public OAuthLogoutSuccessHandler oAuthLogoutSuccessHandler() {
        return new OAuthLogoutSuccessHandler();

    }

    @Bean
    public OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler() {
        return new OAuthAuthenticationSuccessHandler();
    }
    
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {        
        return new CustomAuthenticationFailureHandler();
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


    @Bean
    @Profile("dev")
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
