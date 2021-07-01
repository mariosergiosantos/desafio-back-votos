package br.com.southsystem.cooperativismo.repostory;

import br.com.southsystem.cooperativismo.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
