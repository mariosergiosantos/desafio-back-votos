package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.model.Associate;
import br.com.southsystem.cooperativismo.domain.model.Schedule;
import br.com.southsystem.cooperativismo.domain.request.AssociateRequest;
import br.com.southsystem.cooperativismo.domain.request.ScheduleRequest;
import br.com.southsystem.cooperativismo.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @GetMapping("/v1/associate")
    public List<Associate> getAssociates() {
        return associateService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/associate")
    public Associate newAssociate(@RequestBody @Valid AssociateRequest associateRequest) {
        return associateService.registerNewAssociate(associateRequest);
    }
}
