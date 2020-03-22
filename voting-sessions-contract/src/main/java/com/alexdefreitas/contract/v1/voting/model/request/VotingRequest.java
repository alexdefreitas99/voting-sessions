package com.alexdefreitas.contract.v1.voting.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingRequest {
    private Long agendaId;
    private Long sessionId;
    private String associatedCpf;
    private boolean vote;
}
