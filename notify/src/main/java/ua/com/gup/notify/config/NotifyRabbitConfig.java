package ua.com.gup.notify.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ua.com.gup.notify.receiver.Receiver;

@Configuration
public class NotifyRabbitConfig {

    private static final String queueName = "notifications-queue";
    @Autowired
    private Environment e;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(e.getRequiredProperty("spring.rabbitmq.host"),
                e.getRequiredProperty("spring.rabbitmq.port", Integer.class));
        connectionFactory.setUsername(e.getRequiredProperty("spring.rabbitmq.username"));
        connectionFactory.setPassword(e.getRequiredProperty("spring.rabbitmq.password"));
        connectionFactory.setVirtualHost(e.getRequiredProperty("spring.rabbitmq.virtual-host"));
        return connectionFactory;
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
