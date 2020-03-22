package com.alexdefreitas.voting.service;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.agenda.model.entity.AgendaEntity;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.service.SessionService;
import com.alexdefreitas.voting.mapper.VotingDomainMapper;
import com.alexdefreitas.voting.model.VotingModel;
import com.alexdefreitas.voting.model.entity.VotingEntity;
import com.alexdefreitas.voting.repository.VotingRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotingService {

    private VotingRepository votingRepository;
    private SessionService sessionService;

    public VotingModel vote(VotingModel votingModel) {
        var session = sessionService.findSession(votingModel.getSessionId(), votingModel.getAgendaId());
        VotingDomainMapper.mapFrom(votingRepository.save(buildVotingEntity(votingModel, session)));
        return VotingModel.builder().build();
    }

    private VotingEntity buildVotingEntity(VotingModel votingModel, SessionModel sessionModel) {
        return VotingEntity.builder()
                .vote(votingModel.isVote())
                .agendaEntity(AgendaDomainMapper.mapFrom(sessionModel.getAgendaModel()))
                .associatedCpf(votingModel.getAssociatedCpf())
                .build();
    }
}
