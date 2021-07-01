package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.model.SessionVote;
import br.com.southsystem.cooperativismo.domain.request.SessionVoteRequest;
import br.com.southsystem.cooperativismo.service.SessionVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SessionVoteController {

    @Autowired
    private SessionVoteService sessionVoteService;

    @PostMapping("/v1/session/open-session/schedule/{scheduleId}")
    public SessionVote openSession(@PathVariable("scheduleId") Long scheduleId, @RequestBody @Valid SessionVoteRequest sessionVoteRequest) {
        return sessionVoteService.openSession(sessionVoteRequest);
    }

    @PutMapping("/v1/session/{sessionId}/close")
    public void closeSession(@PathVariable("sessionId") Long sessionId) {
        sessionVoteService.closeSession(sessionId);
    }
}
