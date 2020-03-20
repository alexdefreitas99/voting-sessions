package com.alexdefreitas.agenda.mapper;

import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.agenda.model.entity.AgendaEntity;

public class AgendaMapper {
    public static AgendaEntity mapFrom(AgendaModel agendaModel) {
        return AgendaEntity.builder().subject(agendaModel.getSubject()).build();
    }

    public static AgendaModel mapFrom(AgendaEntity agendaEntity) {
        return AgendaModel.builder().subject(agendaEntity.getSubject()).build();
    }
}
