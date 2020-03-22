package com.alexdefreitas.voting.mapper;

import com.alexdefreitas.voting.model.VotingModel;
import com.alexdefreitas.voting.model.entity.VotingEntity;

public class VotingDomainMapper {
    public static VotingModel mapFrom(VotingEntity votingEntity) {

        return VotingModel
                .builder()
                .id(votingEntity.getId())
                .agendaId(votingEntity.getAgendaEntity().getId())
                .build();
    }
}
