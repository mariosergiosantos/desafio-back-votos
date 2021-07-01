package br.com.southsystem.cooperativismo.domain.request;

import br.com.southsystem.cooperativismo.domain.enumerate.YesNoEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class VoteRequest {

    @NotNull
    @Positive
    private Long associateId;

    @NotNull
    private YesNoEnum vote;

    @NotNull
    @Positive
    private Long scheduleId;

}
