package com.alexdefreitas.agenda.mapper;

import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.agenda.model.entity.AgendaEntity;

import java.util.ArrayList;
import java.util.List;

public class AgendaDomainMapper {
    private AgendaDomainMapper() {
    }

    public static AgendaEntity mapFrom(AgendaModel agendaModel) {
        return AgendaEntity.builder()
                .id(agendaModel.getId())
                .subject(agendaModel.getSubject())
                .build();
    }

    public static AgendaModel mapFrom(AgendaEntity agendaEntity) {
        return AgendaModel.builder()
                .subject(agendaEntity.getSubject())
                .id(agendaEntity.getId())
                .build();
    }

    public static List<AgendaModel> mapFrom(Iterable<AgendaEntity> agendaEntityIterable) {
        List<AgendaModel> agendaModels = new ArrayList<>();

        agendaEntityIterable.forEach(entity -> agendaModels.add(
                AgendaModel
                        .builder()
                        .id(entity.getId())
                        .subject(entity.getSubject())
                        .build()
        ));
        return agendaModels;
    }
}
