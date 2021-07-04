package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.dto.ResultVote;
import br.com.southsystem.cooperativismo.domain.request.VoteRequest;
import br.com.southsystem.cooperativismo.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Operation(summary = "Compute vote")
    @PostMapping("/v1/vote")
    public void computeVote(@RequestBody @Valid VoteRequest voteRequest) {
        voteService.computeVote(voteRequest);
    }

    @Operation(summary = "Get result vote")
    @GetMapping("/v1/vote/schedule/{scheduleId}/count")
    public ResultVote countVote(@PathVariable("scheduleId") Long scheduleId) {
        return voteService.countVote(scheduleId);
    }
}
