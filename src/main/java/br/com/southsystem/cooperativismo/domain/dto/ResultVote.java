package br.com.southsystem.cooperativismo.domain.dto;

import br.com.southsystem.cooperativismo.domain.enumerate.StatusSession;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultVote implements Serializable {

    private static final long serialVersionUID = 1L;

    private String scheduleTitle;

    private Long totalVotes;

    private ScheduleStatistics selectedOption;

    private StatusSession status;

    private List<ScheduleStatistics> statistics;
}
