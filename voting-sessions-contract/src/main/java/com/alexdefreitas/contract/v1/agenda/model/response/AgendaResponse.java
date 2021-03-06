package com.alexdefreitas.contract.v1.agenda.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaResponse {
    private Long id;
    private String subject;
    private Long votesInFavor;
    private Long votesAgainst;
}
