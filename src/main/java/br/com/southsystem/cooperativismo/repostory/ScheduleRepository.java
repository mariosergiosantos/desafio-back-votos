package br.com.southsystem.cooperativismo.repostory;

import br.com.southsystem.cooperativismo.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
