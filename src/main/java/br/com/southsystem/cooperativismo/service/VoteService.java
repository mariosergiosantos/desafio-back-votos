package br.com.southsystem.cooperativismo.service;

import br.com.southsystem.cooperativismo.domain.dto.ValidateCpfResponse;
import br.com.southsystem.cooperativismo.domain.enumerate.StatusUser;
import br.com.southsystem.cooperativismo.domain.model.Associate;
import br.com.southsystem.cooperativismo.domain.model.Schedule;
import br.com.southsystem.cooperativismo.domain.model.SessionVote;
import br.com.southsystem.cooperativismo.domain.model.Vote;
import br.com.southsystem.cooperativismo.domain.request.VoteRequest;
import br.com.southsystem.cooperativismo.exception.BusinessException;
import br.com.southsystem.cooperativismo.exception.NotFoundException;
import br.com.southsystem.cooperativismo.repostory.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AssociateService associateService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SessionVoteService sessionVoteService;

    public void computeVote(VoteRequest voteRequest) {
        Associate associate = associateService.findById(voteRequest.getAssociateId());

        ValidateCpfResponse validateCpfResponse = associateService.validateDocumentAssociate(associate.getCpf());
        if (StatusUser.UNABLE_TO_VOTE.equals(validateCpfResponse.getStatus())) {
            throw new NotFoundException(String.format("Associado com cpf %s não está habilitado a votar", associate.getCpf()));
        }

        Schedule schedule = scheduleService.findById(voteRequest.getScheduleId());
        Optional<SessionVote> optionalSessionVote = sessionVoteService.findSessionOpenBySchedule(schedule.getId());

        if (!optionalSessionVote.isPresent()) {
            throw new BusinessException("Não tem sessão aberta de votação para a pauta");
        }

        voteRepository.save(new Vote(associate, schedule, optionalSessionVote.get(), voteRequest.getVote()));

    }
}
