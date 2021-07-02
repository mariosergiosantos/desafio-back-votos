package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.dto.ResultVote;
import br.com.southsystem.cooperativismo.domain.dto.ScheduleStatistics;
import br.com.southsystem.cooperativismo.domain.model.Vote;
import br.com.southsystem.cooperativismo.domain.request.VoteRequest;
import br.com.southsystem.cooperativismo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/v1/vote")
    public void voteSession(@RequestBody @Valid VoteRequest voteRequest) {
        voteService.computeVote(voteRequest);
    }

    @GetMapping("/v1/vote/schedule/{scheduleId}/count")
    public ResultVote countVote(@PathVariable("scheduleId") Long scheduleId) {
        return voteService.countVote(scheduleId);
    }
}
