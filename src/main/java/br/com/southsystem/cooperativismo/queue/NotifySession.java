package br.com.southsystem.cooperativismo.queue;

import br.com.southsystem.cooperativismo.domain.dto.ResultVote;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class NotifySession {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("sessionEndedQueue")
    private Queue queue;

    public void finishSession(ResultVote resultSession) {
        try {
            rabbitTemplate.convertAndSend(this.queue.getName(), resultSession);
        } catch (Exception e) {
            log.info("Erro ao envia notificação", e.getMessage());
            e.printStackTrace();
        }
    }
}
