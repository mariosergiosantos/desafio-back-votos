package br.com.southsystem.cooperativismo.repostory;

import br.com.southsystem.cooperativismo.domain.enumerate.StatusSession;
import br.com.southsystem.cooperativismo.domain.model.SessionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionVoteRepository extends JpaRepository<SessionVote, Long> {

    Optional<SessionVote> findByStatusSession(StatusSession statusSession);

    SessionVote findByStatusSessionAndFinishAtLessThan(StatusSession statusSession, LocalDateTime now);
}
