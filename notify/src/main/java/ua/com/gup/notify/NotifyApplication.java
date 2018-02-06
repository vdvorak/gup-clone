package ua.com.gup.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ua.com.gup.notify.config.NotifyRabbitConfig;
import ua.com.gup.notify.config.NotifyRootConfig;
import ua.com.gup.notify.config.NotifyWebConfig;

@EnableAutoConfiguration
public class NotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{NotifyWebConfig.class, NotifyRootConfig.class, NotifyRabbitConfig.class, NotifyApplication.class}, args);
    }
}
