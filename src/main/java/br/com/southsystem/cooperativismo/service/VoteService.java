package br.com.southsystem.cooperativismo.service;

import br.com.southsystem.cooperativismo.domain.dto.ResultVote;
import br.com.southsystem.cooperativismo.domain.dto.ScheduleStatistics;
import br.com.southsystem.cooperativismo.domain.dto.ValidateCpfResponse;
import br.com.southsystem.cooperativismo.domain.enumerate.StatusSession;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new BusinessException(String.format("Não tem sessão de votação aberta para a pauta %d", voteRequest.getScheduleId()));
        }

        try {
            voteRepository.save(new Vote(associate, schedule, optionalSessionVote.get(), voteRequest.getVote()));
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException(String.format("Voto já computado para associado %s na pauta %s", associate.getId(), schedule.getTitle()), ex);
        }
    }

    public ResultVote countVote(Long scheduleId) {
        ResultVote resultVote = new ResultVote();
        List<ScheduleStatistics> scheduleStatistics = voteRepository.findByScheduleId(scheduleId);

        Long totalVotes = getTotalVotes(scheduleStatistics);
        ScheduleStatistics winner = getWinner(scheduleStatistics);

        scheduleStatistics = scheduleStatistics.stream()
                .peek(scheduleStatistic -> scheduleStatistic.setTotalVotes(totalVotes)).collect(Collectors.toList());

        Schedule schedule = scheduleService.findById(scheduleId);

        Optional<SessionVote> optionalSessionVote = sessionVoteService.findSessionOpenBySchedule(scheduleId);
        if (optionalSessionVote.isPresent()) {
            resultVote.setStatus(StatusSession.OPEN);
        } else {
            resultVote.setStatus(StatusSession.CLOSE);
        }

        resultVote.setStatistics(scheduleStatistics);
        resultVote.setTotalVotes(totalVotes);
        resultVote.setScheduleTitle(schedule.getTitle());
        resultVote.setSelectedOption(winner);

        return resultVote;
    }

    private Long getTotalVotes(List<ScheduleStatistics> scheduleStatistics) {
        return scheduleStatistics.stream().map(ScheduleStatistics::getCountVotes)
                .reduce(0L, Long::sum);
    }

    private ScheduleStatistics getWinner(List<ScheduleStatistics> scheduleStatistics) {
        return scheduleStatistics.stream()
                .max(Comparator.comparing(ScheduleStatistics::getCountVotes))
                .orElse(null);
    }
}
