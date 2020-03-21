package com.alexdefreitas.session.service;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.agenda.service.AgendaService;
import com.alexdefreitas.session.mapper.SessionDomainMapper;
import com.alexdefreitas.session.model.SessionModel;
import com.alexdefreitas.session.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.alexdefreitas.session.mapper.SessionDomainMapper.mapFrom;

@Service
@AllArgsConstructor
public class SessionService {

    private SessionRepository sessionRepository;
    private AgendaService agendaService;

    public SessionModel createVotingSession(SessionModel sessionModel) {
        var sessionEntity = mapFrom(sessionModel);
        var agendaEntity = AgendaDomainMapper.mapFrom(agendaService.findAgenda(sessionModel.getAgendaId()));
        sessionEntity.setAgenda(agendaEntity);
        return mapFrom(sessionRepository.save(sessionEntity));
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
