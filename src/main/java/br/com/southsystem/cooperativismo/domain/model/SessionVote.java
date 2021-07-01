package br.com.southsystem.cooperativismo.domain.model;

import br.com.southsystem.cooperativismo.domain.enumerate.StatusSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class SessionVote {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    private StatusSession statusSession = StatusSession.OPEN;

    private LocalDateTime finishAt = LocalDateTime.now().plusMinutes(1);

    private LocalDateTime createdAt = LocalDateTime.now();

    public SessionVote() {

    }

    public SessionVote(Schedule schedule, Long timeSession) {
        this.schedule = schedule;
        this.finishAt = LocalDateTime.now().plusMinutes(timeSession == null ? 1 : timeSession);
    }

}
