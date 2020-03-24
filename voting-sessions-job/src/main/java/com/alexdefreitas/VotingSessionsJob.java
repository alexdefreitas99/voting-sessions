package com.alexdefreitas;

import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.model.VotingResults;
import com.alexdefreitas.session.mapper.SessionDomainMapper;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.service.SessionService;
import com.alexdefreitas.voting.service.VotingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class VotingSessionsJob {
    @Autowired
    private AgendaService agendaService;
    @Autowired
    private VotingService votingService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private KafkaProducer agendaModelKafkaProducer;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${agenda.kafka.topic}")
    private String topic;

    private static boolean canClose(SessionModel sessionModel) {
        return !sessionModel.isClosed()
                && sessionModel.getClosingDate().isBefore(LocalDateTime.now());
    }

    @Scheduled(fixedDelayString = "${voting.sessions.job}")
    public void pushAgenda() {
        sessionService.findAll().forEach(sessionModel -> {
            if (canClose(sessionModel)) {
                closeSession(sessionModel);
                sendAgendaToTopic(getVotingResults(sessionModel));
            }
        });
    }

    private void sendAgendaToTopic(VotingResults votingResults) {
        try {
            agendaModelKafkaProducer.sendMessage(votingResults, topic);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    private VotingResults getVotingResults(SessionModel sessionModel) {
        agendaService.calculateVotesBySession(sessionModel);
        return VotingResults.builder().agendaId(sessionModel.getAgendaModel().getId())
                .sessionId(sessionModel.getSessionId())
                .votesAgainst(sessionModel.getAgendaModel().getVotesAgainst())
                .votesInFavor(sessionModel.getAgendaModel().getVotesInFavor())
                .build();
    }

    private void closeSession(SessionModel sessionModel) {
        sessionModel.setClosed(true);
        var sessionEntity = SessionDomainMapper.mapFrom(sessionModel);
        sessionService.save(sessionEntity);
    }
}
