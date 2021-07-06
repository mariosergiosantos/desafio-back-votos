package br.com.southsystem.cooperativismo.service;

import br.com.southsystem.cooperativismo.domain.dto.ValidateCpfResponse;
import br.com.southsystem.cooperativismo.domain.enumerate.StatusUser;
import br.com.southsystem.cooperativismo.domain.model.Associate;
import br.com.southsystem.cooperativismo.domain.request.AssociateRequest;
import br.com.southsystem.cooperativismo.exception.BusinessException;
import br.com.southsystem.cooperativismo.exception.NotFoundException;
import br.com.southsystem.cooperativismo.repostory.AssociateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
@Service
public class AssociateService {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Associate findById(Long associateId) {
        return associateRepository.findById(associateId).orElseThrow(() ->
                new NotFoundException(String.format("Associado com código %d não encontrado", associateId)));
    }

    public List<Associate> findAll() {
        return associateRepository.findAll();
    }

    public Associate registerNewAssociate(AssociateRequest associateRequest) {
        Associate associate;
        try {
            associate = associateRepository.save(new Associate(associateRequest.getName(), associateRequest.getDocument()));
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException(String.format("Associado %s já cadastrado", associateRequest.getDocument()), ex);
        }
        return associate;
    }

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
