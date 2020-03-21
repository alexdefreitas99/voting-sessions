package com.alexdefreitas.contract.v1.session.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionResponse {
    private Long sessionId;
    private Long agendaId;
}
