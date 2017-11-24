package ua.com.gup.rent.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class RentSessionConfig extends AbstractHttpSessionApplicationInitializer {

    @Autowired
    private Environment e;


    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(e.getRequiredProperty("application.session.redis.port", Integer.class));
        jedisConnectionFactory.setHostName(e.getRequiredProperty("application.session.redis.host"));
        return jedisConnectionFactory;
    }

    @Bean
    @Primary
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName(e.getRequiredProperty("application.session.cookie.name"));
        serializer.setCookiePath(e.getRequiredProperty("application.session.cookie.path"));
        return serializer;
    }
}
