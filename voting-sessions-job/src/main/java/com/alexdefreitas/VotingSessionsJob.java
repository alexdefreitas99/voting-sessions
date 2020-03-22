package com.alexdefreitas;

import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.voting.service.VotingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VotingSessionsJob {
    private AgendaService agendaService;
    private VotingService votingService;

//    @Scheduled(fixedDelayString = "${voting.sessions.job}")
    public void a() {
//        agendaService.findAll()
//                .forEach(agendaModel ->
//                        votingService.getVotingSessionsByAgenda(agendaModel)
//                        .forEach(votingModel -> votingModel.get)
//                );
        System.out.println("a");
    }
}
