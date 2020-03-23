package com.alexdefreitas.contract.v1.session.stub;

import com.alexdefreitas.agenda.model.entity.AgendaEntity;
import com.alexdefreitas.session.model.entity.SessionEntity;

import java.time.LocalDateTime;

public class SessionControllerStub {
    public static SessionEntity mockSessionEntity() {
        return SessionEntity.builder()
                .id(1L)
                .agenda(AgendaEntity.builder().id(1L).subject("Mock").build())
                .closingDate(LocalDateTime.now().plusMinutes(10))
                .openingDate(LocalDateTime.now())
                .build();
    }
}
