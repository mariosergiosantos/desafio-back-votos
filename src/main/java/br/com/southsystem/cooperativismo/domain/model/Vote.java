package br.com.southsystem.cooperativismo.domain.model;

import br.com.southsystem.cooperativismo.domain.enumerate.YesNoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Vote {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Associate associate;

    @Enumerated(EnumType.STRING)
    private YesNoEnum vote;

    @OneToOne
    private Schedule schedule;

    @OneToOne
    private SessionVote sessionVote;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Vote() {

    }

    public Vote(Associate associate, Schedule schedule, SessionVote sessionVote, YesNoEnum vote) {
        this.associate = associate;
        this.schedule = schedule;
        this.sessionVote = sessionVote;
        this.vote = vote;
    }
}
