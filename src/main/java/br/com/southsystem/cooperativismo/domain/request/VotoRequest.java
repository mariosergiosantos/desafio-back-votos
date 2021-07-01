package br.com.southsystem.cooperativismo.domain.request;

import lombok.Data;

@Data
public class VotoRequest {

    private Long associateId;

    private String title;
}
