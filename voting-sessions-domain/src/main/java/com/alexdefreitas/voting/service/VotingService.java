package com.alexdefreitas.voting.service;

import com.alexdefreitas.session.mapper.SessionDomainMapper;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.repository.SessionRepository;
import com.alexdefreitas.validate.user.restclient.ValidateUserVote;
import com.alexdefreitas.voting.mapper.VotingDomainMapper;
import com.alexdefreitas.voting.model.VotingModel;
import com.alexdefreitas.voting.model.entity.VotingEntity;
import com.alexdefreitas.voting.repository.VotingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.alexdefreitas.voting.mapper.VotingDomainMapper.mapFrom;

@Service
@AllArgsConstructor
public class VotingService {

    private VotingRepository votingRepository;
    private SessionRepository sessionRepository;
    private ValidateUserVote validateUserVote;

    private static Function<SessionModel, VotingEntity> buildVotingEntity(VotingModel votingModel) {
        return sessionModel -> VotingEntity.builder()
                .vote(votingModel.isVote())
                .session(SessionDomainMapper.mapFrom(sessionModel))
                .associatedCpf(votingModel.getAssociatedCpf())
                .build();
    }

    public VotingModel vote(VotingModel votingModel, Long sessionId, Long agendaId) {

        checkUserHasVotedSession(sessionId)
                .andThen(checkUserIsAbleToVote())
                .accept(votingModel);

        return findAvailableSession()
                .andThen(buildVotingEntity(votingModel))
                .andThen(save())
                .apply(sessionId, agendaId);
    }

    public Stream<VotingModel> findBySessionAgendaId(Long agendaId) {
        return votingRepository.findBySessionAgendaId(agendaId)
                .stream()
                .map(VotingDomainMapper::mapFrom);
    }

    private Consumer<VotingModel> checkUserIsAbleToVote() {
        return votingModel -> validateUserVote.verifyIfUserIsAbleToVote(votingModel.getAssociatedCpf());
    }

    private Consumer<VotingModel> checkUserHasVotedSession(Long sessionId) {
        return votingModel -> {
            boolean userWasVoted = votingRepository
                    .existsByAssociatedCpfAndSessionId(votingModel.getAssociatedCpf(), sessionId);
            if (userWasVoted)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has already voted for this session!");
        };
    }

    private Function<VotingEntity, VotingModel> save() {
        return votingEntity -> mapFrom(votingRepository.save(votingEntity));
    }

    private BiFunction<Long, Long, SessionModel> findAvailableSession() {
        return (sessionId, agendaId) ->
                sessionRepository
                        .findByIdAndAgendaIdAndClosingDateAfter(sessionId, agendaId, LocalDateTime.now())
                        .map(SessionDomainMapper::mapFrom)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "The session ".concat(sessionId.toString()) +
                                        " with agendaId = ".concat(agendaId.toString()) +
                                        " not exists or is closed"
                        ));
    }
}
