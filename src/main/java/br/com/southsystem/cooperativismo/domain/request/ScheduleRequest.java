package br.com.southsystem.cooperativismo.domain.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ScheduleRequest {

    @Size(min = 3, max = 255)
    @NotBlank
    @NotNull
    private String title;
}
