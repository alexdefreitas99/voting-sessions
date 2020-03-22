package com.alexdefreitas.session.model;

import com.alexdefreitas.agenda.model.AgendaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionModel {
    private Long sessionId;
    private Long agendaId;
    private AgendaModel agendaModel;
    private Integer minuteDuration;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
}
