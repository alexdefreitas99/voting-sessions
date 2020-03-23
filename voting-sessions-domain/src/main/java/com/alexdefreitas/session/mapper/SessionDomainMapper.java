package com.alexdefreitas.session.mapper;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.model.entity.SessionEntity;

import java.util.ArrayList;
import java.util.List;

public class SessionDomainMapper {

    public static SessionModel mapFrom(SessionEntity sessionEntity) {
        return SessionModel
                .builder()
                .sessionId(sessionEntity.getId())
                .openingDate(sessionEntity.getOpeningDate())
                .agendaModel(AgendaDomainMapper.mapFrom(sessionEntity.getAgenda()))
                .closingDate(sessionEntity.getClosingDate())
                .closed(sessionEntity.isClosed())
                .build();
    }

    public static SessionEntity mapFrom(SessionModel sessionModel) {
        return SessionEntity.builder()
                .agenda(AgendaDomainMapper.mapFrom(sessionModel.getAgendaModel()))
                .closingDate(sessionModel.getClosingDate())
                .openingDate(sessionModel.getOpeningDate())
                .closed(sessionModel.isClosed())
                .id(sessionModel.getSessionId())
                .build();
    }

    public static List<SessionModel> mapFrom(Iterable<SessionEntity> sessionEntities) {
        List<SessionModel> sessionModels = new ArrayList<>();
        sessionEntities.forEach(sessionEntity -> sessionModels.add(mapFrom(sessionEntity)));
        return sessionModels;
    }
}