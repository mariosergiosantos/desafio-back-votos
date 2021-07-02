package br.com.southsystem.cooperativismo.domain.dto;

import br.com.southsystem.cooperativismo.domain.enumerate.StatusSession;
import br.com.southsystem.cooperativismo.domain.enumerate.YesNoEnum;
import lombok.Data;

import java.util.List;

@Data
public class ResultVote {

    private String scheduleTitle;

    private Long totalVotes;

    private YesNoEnum selectedOption;

    private StatusSession status;

    private List<ScheduleStatistics> statistics;
}
