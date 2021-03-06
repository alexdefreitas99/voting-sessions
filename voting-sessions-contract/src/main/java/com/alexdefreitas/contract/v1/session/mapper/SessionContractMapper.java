package com.alexdefreitas.contract.v1.session.mapper;

import com.alexdefreitas.contract.v1.session.model.request.SessionRequest;
import com.alexdefreitas.contract.v1.session.model.response.SessionResponse;
import com.alexdefreitas.session.model.SessionModel;

public class SessionContractMapper {
    public static SessionModel mapFrom(SessionRequest sessionRequest) {
        return SessionModel.builder()
                .minuteDuration(sessionRequest.getMinuteDuration())
                .build();
    }

    public static SessionResponse mapFrom(SessionModel sessionModel) {
        return SessionResponse.builder()
                .sessionId(sessionModel.getSessionId())
                .openingDate(sessionModel.getOpeningDate().toString())
                .closingDate(sessionModel.getClosingDate().toString())
                .agendaId(sessionModel.getAgendaModel().getId())
                .build();
    }
}
