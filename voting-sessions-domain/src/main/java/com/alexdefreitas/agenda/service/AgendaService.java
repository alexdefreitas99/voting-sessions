package com.alexdefreitas.agenda.service;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.agenda.repository.AgendaRepository;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.voting.model.VotingModel;
import com.alexdefreitas.voting.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

import static com.alexdefreitas.agenda.mapper.AgendaDomainMapper.mapFrom;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private VotingService votingService;

    private static void updateVotes(AgendaModel agendaModel, VotingModel votingModel) {
        if (votingModel.isVote()) agendaModel.setVotesInFavor(agendaModel.getVotesInFavor() + 1);
        else agendaModel.setVotesAgainst(agendaModel.getVotesAgainst() + 1);
    }

    public AgendaModel createAgenda(AgendaModel agendaModel) {
        return mapFrom(agendaRepository.save(mapFrom(agendaModel)));
    }

    public AgendaModel getAgenda(Long agendaId) {
        return agendaRepository.findById(agendaId)
                .map(AgendaDomainMapper::mapFrom)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda not found"));
    }

    public AgendaModel getAgendaCalculedVotes(Long agendaId) {
        var agendaModel = getAgenda(agendaId);
        updateTotalVotes(agendaModel);
        return agendaModel;
    }

    private void updateTotalVotes(AgendaModel agendaModel) {
        var votingSessions = getBySessionAgendaId(agendaModel);
        votingSessions.forEach(votingModel -> updateVotes(agendaModel, votingModel));
    }

    private Stream<VotingModel> getBySessionAgendaId(AgendaModel agendaModel) {
        return votingService.findBySessionAgendaId(agendaModel.getId());
    }

    public void calculateVotesBySession(SessionModel sessionModel) {
        var votingSessions = getBySessionAgendaId(sessionModel.getAgendaModel());
        votingSessions.forEach(votingModel -> {
            if (votingModel.getSession().getSessionId().equals(sessionModel.getSessionId())) {
                updateVotes(sessionModel.getAgendaModel(), votingModel);
            }
        });
    }
}
