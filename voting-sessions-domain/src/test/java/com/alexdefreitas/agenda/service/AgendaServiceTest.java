package com.alexdefreitas.agenda.service;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.agenda.repository.AgendaRepository;
import com.alexdefreitas.voting.service.VotingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Stream;

import static com.alexdefreitas.agenda.service.AgendaServiceStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private VotingService votingService;

    @InjectMocks
    private AgendaService agendaService;

    @Test
    public void mustCreateAnAgendaWithSuccessful() {
        when(agendaRepository.save(any())).thenReturn(mockAgendaEntity());
        assertEquals(AgendaDomainMapper.mapFrom(mockAgendaEntity()), agendaService.createAgenda(mockAgendaModel()));
    }

    @Test
    public void mustReturnAExistingAgenda() {
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(mockAgendaEntity()));
        assertEquals(AgendaDomainMapper.mapFrom(mockAgendaEntity()), agendaService.getAgenda(mockAgendaEntity().getId()));
    }

    @Test(expected = ResponseStatusException.class)
    public void mustReturnAResponseStatusException() {
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.empty());
        agendaService.getAgenda(2L);
    }

    @Test
    public void mustReturnAgendaWithCalculatedVotes() {
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(mockAgendaEntity()));
        when(votingService.findBySessionAgendaId(anyLong()))
                .thenReturn(Stream.of(mockVotingModel(), mockVotingModel()));
        agendaService.getAgenda(2L);
        assertEquals(mockAgendaModelWithCalculatedVotes(), agendaService.getAgendaCalculedVotes(1L));
    }

    @Test
    public void mustUpdateTheVotesOfSession() {
        var votingModelDiferentSession = mockVotingModel();
        var votingModel = mockVotingModel();

        votingModelDiferentSession.getSession().setSessionId(12L);

        when(votingService.findBySessionAgendaId(any()))
                .thenReturn(Stream.of(votingModel, votingModelDiferentSession));

        agendaService.calculateVotesBySession(votingModel.getSession());
        assertEquals(0L, (long) votingModel.getSession().getAgendaModel().getVotesAgainst());
        assertEquals(1L, (long) votingModel.getSession().getAgendaModel().getVotesInFavor());
    }
}