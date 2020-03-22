package com.alexdefreitas.contract.v1.agenda;

import com.alexdefreitas.agenda.repository.AgendaRepository;
import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.session.repository.SessionRepository;
import com.alexdefreitas.session.service.SessionService;
import com.alexdefreitas.voting.repository.VotingRepository;
import com.alexdefreitas.voting.service.VotingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.alexdefreitas.contract.v1.agenda.stub.AgendaControllerStub.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@WebMvcTest(AgendaController.class)
@SpringBootTest
public class AgendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgendaService agendaService;
    @Autowired
    private VotingService votingService;
    @Autowired
    private SessionService sessionService;

    @MockBean
    private VotingRepository votingRepository;
    @MockBean
    private AgendaRepository agendaRepository;
    @MockBean
    private SessionRepository sessionRepository;

    @Test
    public void createAgendaWithSucess() throws Exception {
        String json = new ObjectMapper().writeValueAsString(mockAgendaRequest());
        when(agendaRepository.save(any())).thenReturn(mockAgendaEntity());

        this.mockMvc.perform(post("/v1/agenda")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").value(mockAgendaResponse().getId()))
                .andExpect(jsonPath("$.subject").value(mockAgendaResponse().getSubject()))
                .andExpect(jsonPath("$.votesAgainst").value(mockAgendaResponse().getVotesAgainst()))
                .andExpect(jsonPath("$.votesInFavor").value(mockAgendaResponse().getVotesInFavor()));
    }
}
