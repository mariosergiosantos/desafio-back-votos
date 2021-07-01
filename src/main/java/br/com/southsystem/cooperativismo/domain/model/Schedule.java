package br.com.southsystem.cooperativismo.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Schedule {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Schedule() {

    }

    public Schedule(String title) {
        this.title = title;
    }

}
