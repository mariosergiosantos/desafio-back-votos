package br.com.southsystem.cooperativismo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${queue.session-ended.name}")
    private String sessionEndedName;

    @Bean
    public Queue sessionEndedName() {
        return new Queue(sessionEndedName, true);
    }
}
