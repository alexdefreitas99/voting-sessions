package com.alexdefreitas.session.service;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.session.mapper.SessionDomainMapper;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.model.entity.SessionEntity;
import com.alexdefreitas.session.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static com.alexdefreitas.session.mapper.SessionDomainMapper.mapFrom;

@Service
@AllArgsConstructor
public class SessionService {

    private SessionRepository sessionRepository;
    private AgendaService agendaService;

    public SessionModel createVotingSession(SessionModel sessionModel) {
        var agendaEntity = AgendaDomainMapper.mapFrom(agendaService.findAgenda(sessionModel.getAgendaId()));
        var sessionEntity = SessionEntity.builder()
                .agenda(agendaEntity)
                .closingDate(getSessionClosingDate(sessionModel.getMinuteDuration()))
                .build();
        return mapFrom(sessionRepository.save(sessionEntity));
    }

    private static LocalDateTime getSessionClosingDate(Integer minuteDuration) {
        return LocalDateTime.now().plusMinutes(minuteDuration);
    }

    public SessionModel findSession(Long id) {
        return sessionRepository.findById(id)
                .map(SessionDomainMapper::mapFrom)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Session " + id.toString() + " not found."
                ));
    }
}
