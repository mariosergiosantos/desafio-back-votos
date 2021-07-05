package br.com.southsystem.cooperativismo.scheduled;

import br.com.southsystem.cooperativismo.service.SessionVoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ScheduledTasks {

    private static final int MINUTES = 1;

    @Autowired
    private SessionVoteService sessionVoteService;

    @Scheduled(fixedRate = (MINUTES * 5000 * 60))
    public void scheduledCloseSessionOpen() {
        sessionVoteService.closeSessionVoteExpire();
    }
}
