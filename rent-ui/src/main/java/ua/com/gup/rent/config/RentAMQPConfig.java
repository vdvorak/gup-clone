package ua.com.gup.rent.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RentAMQPConfig {

    public static final String queueName = "notifications-queue";

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

    @Bean(name = "rabbitProducerTemplate")
    public RabbitTemplate rabbitProducerTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setQueue(queueName);
        rabbitTemplate.setRoutingKey(queueName);
        return rabbitTemplate;
    }


    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

}
