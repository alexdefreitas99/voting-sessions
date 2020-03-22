package com.alexdefreitas.voting.mapper;

import com.alexdefreitas.session.mapper.SessionDomainMapper;
import com.alexdefreitas.voting.model.VotingModel;
import com.alexdefreitas.voting.model.entity.VotingEntity;

import java.util.ArrayList;
import java.util.List;

public class VotingDomainMapper {
    private VotingDomainMapper() {

    }

    public static VotingModel mapFrom(VotingEntity votingEntity) {
        return VotingModel
                .builder()
                .associatedCpf(votingEntity.getAssociatedCpf())
                .session(SessionDomainMapper.mapFrom(votingEntity.getSessionEntity()))
                .id(votingEntity.getId())
                .vote(votingEntity.isVote())
                .build();
    }

    public static List<VotingModel> mapFrom(List<VotingEntity> votingEntities) {
        List<VotingModel> votingModels = new ArrayList<>();

        votingEntities.forEach(entity -> votingModels.add(
                VotingModel
                        .builder()
                        .id(entity.getId())
                        .session(SessionDomainMapper.mapFrom(entity.getSessionEntity()))
                        .associatedCpf(entity.getAssociatedCpf())
                        .vote(entity.isVote())
                        .build()
        ));
        return votingModels;
    }
}
