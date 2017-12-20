package ua.com.gup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;
import ua.com.gup.token.CustomTokenEnhancer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier(value = "GupClientDetailsService")
    private ClientDetailsService clientDetailService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .pathMapping("/oauth/token", "/api/oauth/token")
                .pathMapping("/oauth/check_token", "/api/oauth/check_token")
                .pathMapping("/oauth/authorize", "/api/oauth/authorize")
                .tokenEnhancer(tokenEnhancer())
                .authenticationManager(authenticationManager)
                .addInterceptor(new HandlerInterceptorAdapter() {
                    @Override
                    public void postHandle(HttpServletRequest request,
                                           HttpServletResponse response, Object handler,
                                           ModelAndView modelAndView) throws Exception {
                        if (modelAndView != null
                                && modelAndView.getView() instanceof RedirectView) {
                            RedirectView redirect = (RedirectView) modelAndView.getView();
                            String url = redirect.getUrl();
                            if (url.contains("code=") || url.contains("error=")) {
                                HttpSession session = request.getSession(false);
                                if (session != null) {
                                    session.invalidate();
                                }
                            }
                        }
                    }
                });

    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

    @Bean
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        oauthAuthenticationEntryPoint.setRealmName("GupRealmName");
        return oauthAuthenticationEntryPoint;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }


}
