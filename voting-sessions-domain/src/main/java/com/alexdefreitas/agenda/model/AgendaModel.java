package com.alexdefreitas.agenda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendaModel {
    private Long id;
    private String subject;
    @Builder.Default
    private Long votesInFavor = 0L;
    @Builder.Default
    private Long votesAgainst = 0L;
}
