package br.com.southsystem.cooperativismo.domain.dto;

import br.com.southsystem.cooperativismo.domain.enumerate.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCpfResponse {

    private StatusUser status;
}
