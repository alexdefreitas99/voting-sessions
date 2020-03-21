package com.alexdefreitas.session.mapper;

import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.model.entity.SessionEntity;

public class SessionDomainMapper {

    public static SessionModel mapFrom(SessionEntity sessionEntity) {
        return SessionModel
                .builder()
                .sessionId(sessionEntity.getId())
                .openingDate(sessionEntity.getOpeningDate())
                .agendaId(sessionEntity.getAgenda().getId())
                .closingDate(sessionEntity.getClosingDate())
                .build();
    }
}
