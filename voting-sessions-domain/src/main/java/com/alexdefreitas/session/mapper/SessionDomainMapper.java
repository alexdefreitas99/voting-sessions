package com.alexdefreitas.session.mapper;

import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.model.entity.SessionEntity;

public class SessionDomainMapper {
    public static SessionEntity mapFrom(SessionModel sessionModel) {
        return SessionEntity.builder().build();
    }

    public static SessionModel mapFrom(SessionEntity sessionEntity) {
        return SessionModel
                .builder()
                .agendaId(sessionEntity.getId())
                .build();
    }
}
