package com.alexdefreitas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingResults {
    private Long sessionId;
    private Long agendaId;
    private Long votesAgainst;
    private Long votesInFavor;
}
