package com.alexdefreitas.voting.model.entity;

import com.alexdefreitas.agenda.model.entity.AgendaEntity;
import com.alexdefreitas.session.model.entity.SessionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "voting")
public class VotingEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "associated_cpf")
    private String associatedCpf;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private AgendaEntity agendaEntity;

    private boolean vote;
}
