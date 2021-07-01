package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.request.VoteRequest;
import br.com.southsystem.cooperativismo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    //Receber votos dos associados em pautas
    @PostMapping("/v1/vote")
    public void voteSession(@RequestBody @Valid VoteRequest voteRequest) {
        voteService.computeVote(voteRequest);
    }

    //Contabilizar os votos e dar o resultado da votação na pauta
    @GetMapping("/v1/session/{sessionId}/count-vote")
    public String countVote() throws InterruptedException {
        Thread.sleep(31000);
        return "ABLE_TO_VOTE";
    }
}
