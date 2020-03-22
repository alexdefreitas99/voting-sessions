package com.alexdefreitas.voting.model;

import com.alexdefreitas.agenda.model.AgendaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotingModel {
    private Long id;
    private Long agendaId;
    private Long sessionId;
    private String associatedCpf;
    private boolean vote;
}
