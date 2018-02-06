package ua.com.gup.notify.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.notify.receiver.Receiver;
import ua.com.gup.notify.service.NotificationService;

@Configuration
@ComponentScan(basePackageClasses = {Receiver.class, NotificationService.class})
public class NotifyRootConfig {
}
