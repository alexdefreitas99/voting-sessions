package com.alexdefreitas.contract.v1.agenda.mapper;

import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.contract.v1.agenda.model.request.AgendaRequest;
import com.alexdefreitas.contract.v1.agenda.model.response.AgendaResponse;

public class AgendaMapper {
    public static AgendaModel mapFrom(AgendaRequest agendaRequest) {
        return AgendaModel
                .builder()
                .subject(agendaRequest.getSubject())
                .build();
    }

    public static AgendaResponse mapFrom(AgendaModel agendaModel) {
        return AgendaResponse
                .builder()
                .status("Ok")
                .build();
    }
}
