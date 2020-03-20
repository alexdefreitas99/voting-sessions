package com.alexdefreitas.agenda.mapper;

import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.agenda.model.entity.AgendaEntity;

import java.util.ArrayList;
import java.util.List;

public class AgendaModelMapper {
    public static AgendaEntity mapFrom(AgendaModel agendaModel) {
        return AgendaEntity.builder().subject(agendaModel.getSubject()).build();
    }

    public static AgendaModel mapFrom(AgendaEntity agendaEntity) {
        return AgendaModel.builder().subject(agendaEntity.getSubject()).build();
    }

    public static List<AgendaModel> mapFrom(Iterable<AgendaEntity> agendaEntityIterable) {
        List<AgendaModel> agendaModels = new ArrayList<>();

        agendaEntityIterable.forEach(i -> agendaModels.add(
                AgendaModel
                        .builder()
                        .subject(i.getSubject())
                        .build()
        ));
        return agendaModels;
    }
}
