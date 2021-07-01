package br.com.southsystem.cooperativismo.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class Vote {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Associate associate;

    @OneToOne
    private Schedule schedule;

    @OneToOne
    private SessionVote sessionVote;
}
