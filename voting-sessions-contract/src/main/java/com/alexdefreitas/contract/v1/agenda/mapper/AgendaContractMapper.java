package com.alexdefreitas.contract.v1.agenda.mapper;

import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.contract.v1.agenda.model.request.AgendaRequest;
import com.alexdefreitas.contract.v1.agenda.model.response.AgendaResponse;

import java.util.ArrayList;
import java.util.List;

public class AgendaContractMapper {

    private AgendaContractMapper() { }

    public static AgendaModel mapFrom(AgendaRequest agendaRequest) {
        return AgendaModel
                .builder()
                .subject(agendaRequest.getSubject())
                .build();
    }

    public static AgendaResponse mapFrom(AgendaModel agendaModel) {
        return AgendaResponse
                .builder()
                .id(agendaModel.getId())
                .subject(agendaModel.getSubject())
                .build();
    }

    public static List<AgendaResponse> mapFrom(List<AgendaModel> agendaModels) {
        List<AgendaResponse> agendaResponses = new ArrayList<>();
        agendaModels
                .parallelStream()
                .forEach(agendaModel -> agendaResponses.add(
                        AgendaResponse
                                .builder()
                                .id(agendaModel.getId())
                                .subject(agendaModel.getSubject())
                                .build()
                ));
        return agendaResponses;
    }
}
