package br.com.southsystem.cooperativismo.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SessionVoteRequest {

    @NotNull
    private Long scheduleId;

    @Positive
    private Long timeSession;

}
