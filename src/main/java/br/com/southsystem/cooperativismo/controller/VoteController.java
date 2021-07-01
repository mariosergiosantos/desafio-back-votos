package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.request.VoteRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VoteController {

    //Receber votos dos associados em pautas
    @PostMapping("/v1/session/{sessionId}/vote")
    public void voteSession(@RequestBody @Valid VoteRequest voteRequest) {

    }

    //Contabilizar os votos e dar o resultado da votação na pauta
    @GetMapping("/v1/session/{sessionId}/count-vote")
    public String countVote() throws InterruptedException {
        Thread.sleep(31000);
        return "ABLE_TO_VOTE";
    }
}
