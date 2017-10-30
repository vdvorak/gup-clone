package ua.com.gup.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.vote.ScopeVoter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import ua.com.gup.config.oauth2.TokenStoreService;
import ua.com.gup.service.client.ClientDetailService;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@ImportResource(value = {"classpath:spring/security/spring-security.xml"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private ClientDetailService clientDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationEntryPoint oauthAuthenticationEntryPoint;

    /* @Autowired
    private AuthenticationEntryPoint clientAuthenticationEntryPoint;*/

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

   /* @Autowired
    private ClientDetailsUserDetailsService clientDetailsUserDetailsService;*/

  /*  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().maximumSessions(1);
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(oauthAuthenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and();

    }*/
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);

    }*/


    // ################################ dependencies  Bean for OAuth2 ###############################################

    @Bean(name = "resourceServerFilter")
    public OAuth2AuthenticationProcessingFilter qOAuth2AuthenticationProcessingFilter() {
        OAuth2AuthenticationProcessingFilter processingFilter = new OAuth2AuthenticationProcessingFilter();
        processingFilter.setAuthenticationManager(this.auth2AM());

        return processingFilter;
    }
    private OAuth2AuthenticationManager auth2AM() {
        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
        //oAuth2AuthenticationManager.setResourceId("crophub_oauth_server");
        oAuth2AuthenticationManager.setTokenServices(tokenServices());
        return oAuth2AuthenticationManager;
    }


@Bean
public UnanimousBased accessDecisionManager(){
    List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
    decisionVoters.add(new ScopeVoter());
    decisionVoters.add(new RoleVoter());
    decisionVoters.add(new AuthenticatedVoter());
    UnanimousBased accessDecisionManager =new UnanimousBased(decisionVoters);
    return accessDecisionManager;
}
    @Bean
   public DefaultTokenServices tokenServices(){
       DefaultTokenServices tokenServices = new DefaultTokenServices();
       tokenServices.setSupportRefreshToken(true);
       tokenServices.setTokenStore(tokenStore());
       tokenServices.setClientDetailsService(clientDetailService);
       return tokenServices;
   }

    @Bean
    public TokenStore tokenStore() {
        return new TokenStoreService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler(){
        return new OAuth2AccessDeniedHandler();
    }

    @Bean
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint(){
        OAuth2AuthenticationEntryPoint  oauthAuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        oauthAuthenticationEntryPoint.setRealmName("test");
        return oauthAuthenticationEntryPoint;
    }

    @Bean
    public OAuth2AuthenticationEntryPoint clientAuthenticationEntryPoint(){
        OAuth2AuthenticationEntryPoint  oauthAuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        oauthAuthenticationEntryPoint.setTypeName("Basic");
        return oauthAuthenticationEntryPoint;
    }

    //session manager bean
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}

