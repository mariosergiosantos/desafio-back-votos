package br.com.southsystem.cooperativismo.queue.listener;

import br.com.southsystem.cooperativismo.domain.dto.ResultVote;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ReceiverNotifySession {

    @RabbitListener(queues = {"${queue.session-ended.name}"})
    public void receive(@Payload ResultVote resultSession) {
        log.info("receiver message");
        log.info("schedule {}", resultSession.getScheduleTitle());
        log.info("result schedule: Win -> {}", resultSession.getSelectedOption().getOption());
        log.info("winne with {}", resultSession.getSelectedOption().getPercent());
    }
}
