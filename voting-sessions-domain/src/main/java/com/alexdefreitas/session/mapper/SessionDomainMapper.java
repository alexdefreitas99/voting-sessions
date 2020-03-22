package com.alexdefreitas.session.mapper;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.model.entity.SessionEntity;

public class SessionDomainMapper {

    public static SessionModel mapFrom(SessionEntity sessionEntity) {
        return SessionModel
                .builder()
                .sessionId(sessionEntity.getId())
                .openingDate(sessionEntity.getOpeningDate())
                .agendaModel(AgendaDomainMapper.mapFrom(sessionEntity.getAgenda()))
                .closingDate(sessionEntity.getClosingDate())
                .build();
    }
}
