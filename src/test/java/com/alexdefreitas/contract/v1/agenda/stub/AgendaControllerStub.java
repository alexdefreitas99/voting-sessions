package com.alexdefreitas.contract.v1.agenda.stub;

import com.alexdefreitas.agenda.model.entity.AgendaEntity;
import com.alexdefreitas.contract.v1.agenda.model.request.AgendaRequest;
import com.alexdefreitas.contract.v1.agenda.model.response.AgendaResponse;

public class AgendaControllerStub {
    public static AgendaRequest mockAgendaRequest() {
        return AgendaRequest.builder().subject("Mock").build();
    }
    public  static AgendaEntity mockAgendaEntity() {
        return AgendaEntity.builder().id(1L).subject(mockAgendaRequest().getSubject()).build();
    }
    public static AgendaResponse mockAgendaResponse(){
        return AgendaResponse
                .builder()
                .votesInFavor(0L)
                .votesAgainst(0L)
                .id(mockAgendaEntity().getId())
                .subject(mockAgendaEntity().getSubject())
                .build();
    }
}
