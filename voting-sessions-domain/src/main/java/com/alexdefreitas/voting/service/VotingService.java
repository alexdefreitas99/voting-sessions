package com.alexdefreitas.voting.service;

import com.alexdefreitas.session.mapper.SessionDomainMapper;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.service.SessionService;
import com.alexdefreitas.voting.model.VotingModel;
import com.alexdefreitas.voting.model.entity.VotingEntity;
import com.alexdefreitas.voting.repository.VotingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.alexdefreitas.voting.mapper.VotingDomainMapper.mapFrom;

@Service
@AllArgsConstructor
public class VotingService {

    private VotingRepository votingRepository;
    private SessionService sessionService;

    private static Function<SessionModel, VotingEntity> buildVotingEntity(VotingModel votingModel) {
        return sessionModel -> VotingEntity.builder()
                .vote(votingModel.isVote())
                .sessionEntity(SessionDomainMapper.mapFrom(sessionModel))
                .associatedCpf(votingModel.getAssociatedCpf())
                .build();
    }

    public VotingModel vote(VotingModel votingModel, Long sessionId, Long agendaId) {
        return findAvailableSession()
                .andThen(buildVotingEntity(votingModel))
                .andThen(save())
                .apply(sessionId, agendaId);
    }

    private Function<VotingEntity, VotingModel> save() {
        return votingEntity -> mapFrom(votingRepository.save(votingEntity));
    }

    private BiFunction<Long, Long, SessionModel> findAvailableSession() {
        return (sessionId, agendaId) -> sessionService.findAvailableVotingSession(sessionId, agendaId);
    }

//    public List<VotingModel> getVotingSessionsByAgenda(AgendaModel agendaModel) {
//        return mapFrom(votingRepository.findByAgenda(AgendaDomainMapper.mapFrom(agendaModel)));
//    }
}
