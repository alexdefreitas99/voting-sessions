package com.alexdefreitas.contract.v1.session;

import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.session.repository.SessionRepository;
import com.alexdefreitas.session.service.SessionService;
import com.alexdefreitas.voting.service.VotingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.alexdefreitas.contract.v1.session.stub.SessionControllerStub.mockSessionEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgendaService agendaService;
    @Autowired
    private VotingService votingService;
    @Autowired
    private SessionService sessionService;

    @MockBean
    private SessionRepository sessionRepository;

    @Test
    public void getSessionThatExists() throws Exception {
        var mockSession = mockSessionEntity();
        when(sessionRepository.findById(any())).thenReturn(Optional.of(mockSession));

        this.mockMvc.perform(get("/v1/session/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.sessionId").value(mockSession.getId()))
                .andExpect(jsonPath("$.agendaId").value(mockSession.getAgenda().getId()))
                .andExpect(jsonPath("$.openingDate").value(mockSession.getOpeningDate().toString()))
                .andExpect(jsonPath("$.closingDate").value(mockSession.getClosingDate().toString()));
    }

    @Test
    public void getSessionThatNotExists() throws Exception {
        when(sessionRepository.findById(any())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/v1/session/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(status().is(404));
    }
}
