package br.com.southsystem.cooperativismo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${queue.session-ended.name}")
    private String sessionEndedQueueName;

    @Bean
    public Queue sessionEndedQueue() {
        return new Queue(sessionEndedQueueName, true);
    }
}
