package com.alexdefreitas.session.model;

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
    private Integer minuteDuration;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
}
