package com.alexdefreitas.agenda.service;

import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.agenda.model.entity.AgendaEntity;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.voting.model.VotingModel;

public class AgendaServiceStub {
    public static AgendaEntity mockAgendaEntity() {
        return AgendaEntity
                .builder()
                .id(1L)
                .subject("Mock")
                .build();
    }

    public static AgendaModel mockAgendaModel() {
        return AgendaModel.builder()
                .subject(mockAgendaEntity().getSubject())
                .build();
    }

    public static VotingModel mockVotingModel() {
        return VotingModel.builder()
                .associatedCpf("54125851085")
                .id(1L)
                .session(SessionModel.builder().agendaModel(AgendaModel.builder().build()).sessionId(1L).build())
                .vote(true)
                .build();
    }
    public static AgendaModel mockAgendaModelWithCalculatedVotes(){
        var agendaModel = mockAgendaModel();
        agendaModel.setId(1L);
        agendaModel.setVotesAgainst(0L);
        agendaModel.setVotesInFavor(2L);
        return agendaModel;
    }
}
