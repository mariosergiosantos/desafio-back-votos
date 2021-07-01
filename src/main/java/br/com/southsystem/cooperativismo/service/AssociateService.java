package br.com.southsystem.cooperativismo.service;

import br.com.southsystem.cooperativismo.domain.dto.ValidateCpfResponse;
import br.com.southsystem.cooperativismo.domain.enumerate.StatusUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class AssociateService {

    @Autowired
    private RestTemplate restTemplate;

    public ValidateCpfResponse validateDocumentAssociate(String cpf) {
        log.info("INICIANDO VALIDAÇÃO DO CPF NA API EXTERNA {}", cpf);
        ValidateCpfResponse response;

        try {
            response = restTemplate.getForObject(String.format("https://user-info.herokuapp.com/users/%s", cpf), ValidateCpfResponse.class);
            log.info("CONSULTA DA VALIDAÇÃO EFETUADA COM SUCESSO RESPONSE - {}", response);
        } catch (ResourceAccessException e) {
            log.info("ERRO AO CONSULTAR API DE VALIDAÇÃO DE CPF");
            response = new ValidateCpfResponse(StatusUser.UNABLE_TO_VOTE);
        }

        return response;
    }
}
