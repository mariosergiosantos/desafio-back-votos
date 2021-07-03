package br.com.southsystem.cooperativismo.service;

import br.com.southsystem.cooperativismo.domain.dto.ResultVote;
import br.com.southsystem.cooperativismo.domain.enumerate.StatusSession;
import br.com.southsystem.cooperativismo.domain.model.Schedule;
import br.com.southsystem.cooperativismo.domain.model.SessionVote;
import br.com.southsystem.cooperativismo.domain.request.SessionVoteRequest;
import br.com.southsystem.cooperativismo.exception.BusinessException;
import br.com.southsystem.cooperativismo.exception.NotFoundException;
import br.com.southsystem.cooperativismo.queue.NotifySession;
import br.com.southsystem.cooperativismo.repostory.SessionVoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class SessionVoteService {

    @Autowired
    private SessionVoteRepository sessionVoteRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private NotifySession notifySession;

    @Autowired
    private VoteService voteService;

    public SessionVote openSession(SessionVoteRequest sessionVoteRequest) {
        Schedule schedule = scheduleService.findById(sessionVoteRequest.getScheduleId());

        sessionVoteRepository.findByStatusSession(StatusSession.OPEN).ifPresent(sessionVote -> {
            throw new BusinessException("Já Existe uma sessão de votação aberta");
        });

        return sessionVoteRepository.save(new SessionVote(schedule, sessionVoteRequest.getTimeSession()));
    }

    public void closeSessionVoteExpire() {
        SessionVote sessionVote = sessionVoteRepository.findByStatusSessionAndFinishAtLessThan(StatusSession.OPEN, LocalDateTime.now());
        if (sessionVote != null) {
            log.info("Has session votes open {}", sessionVote);
            sessionVote.setStatusSession(StatusSession.CLOSE);
            sessionVoteRepository.save(sessionVote);
            notifySession(sessionVote);
            log.info("close session votes expires");
        }
    }

    public void closeSession(Long sessionId) {
        SessionVote sessionVote = sessionVoteRepository.findByIdAndStatusSession(sessionId, StatusSession.OPEN).orElseThrow(() ->
                new NotFoundException(String.format("Sessão com código %d não encontrado ou já está fechado", sessionId)));

        sessionVote.setStatusSession(StatusSession.CLOSE);
        sessionVoteRepository.save(sessionVote);
        notifySession(sessionVote);
    }

    public void notifySession(SessionVote sessionVote) {
        ResultVote resultSession = voteService.countVote(sessionVote.getSchedule().getId());
        notifySession.finishSession(resultSession);
    }

    public Optional<SessionVote> findSessionOpenBySchedule(Long scheduleId) {
        return sessionVoteRepository.findByStatusSessionAndScheduleId(StatusSession.OPEN, scheduleId);
    }

    public List<SessionVote> findAll() {
        return sessionVoteRepository.findAll();
    }
}
