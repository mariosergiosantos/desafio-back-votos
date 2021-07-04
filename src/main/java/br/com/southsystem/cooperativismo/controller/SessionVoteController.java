package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.model.SessionVote;
import br.com.southsystem.cooperativismo.domain.request.SessionVoteRequest;
import br.com.southsystem.cooperativismo.service.SessionVoteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SessionVoteController {

    @Autowired
    private SessionVoteService sessionVoteService;

    @Operation(summary = "List associate")
    @GetMapping("/v1/session")
    public List<SessionVote> getAllSessions() {
        return sessionVoteService.findAll();
    }

    @Operation(summary = "Open session")
    @PostMapping("/v1/session/open")
    public SessionVote openSession(@RequestBody @Valid SessionVoteRequest sessionVoteRequest) {
        return sessionVoteService.openSession(sessionVoteRequest);
    }

    @Operation(summary = "Close session")
    @PutMapping("/v1/session/{sessionId}/close")
    public void closeSession(@PathVariable("sessionId") Long sessionId) {
        sessionVoteService.closeSession(sessionId);
    }
}
