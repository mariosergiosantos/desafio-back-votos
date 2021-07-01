package br.com.southsystem.cooperativismo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Wrapper {

    private int status;
    private String title;
    private String detail;
    private LocalDateTime date;
    private String path;
    private Map<String, String> errors;

}
