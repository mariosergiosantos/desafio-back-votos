package br.com.southsystem.cooperativismo.repostory;

import br.com.southsystem.cooperativismo.domain.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {
}
