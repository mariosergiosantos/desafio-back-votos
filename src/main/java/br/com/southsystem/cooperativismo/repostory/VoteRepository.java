package br.com.southsystem.cooperativismo.repostory;

import br.com.southsystem.cooperativismo.domain.dto.ScheduleStatistics;
import br.com.southsystem.cooperativismo.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT new br.com.southsystem.cooperativismo.domain.dto.ScheduleStatistics(v.vote, COUNT(v)) from Vote v " +
            "JOIN v.schedule schedule " +
            "WHERE schedule.id = :scheduleId " +
            "GROUP BY v.vote")
    List<ScheduleStatistics> findByScheduleId(Long scheduleId);
}
