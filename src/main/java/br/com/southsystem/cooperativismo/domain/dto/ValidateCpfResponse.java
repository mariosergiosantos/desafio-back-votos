package br.com.southsystem.cooperativismo.domain.dto;

import br.com.southsystem.cooperativismo.domain.enumerate.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateCpfResponse {

    private StatusUser status;
}
