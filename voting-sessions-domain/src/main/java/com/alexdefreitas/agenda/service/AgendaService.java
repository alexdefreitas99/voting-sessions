package com.alexdefreitas.agenda.service;

import com.alexdefreitas.agenda.mapper.AgendaDomainMapper;
import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.agenda.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.alexdefreitas.agenda.mapper.AgendaDomainMapper.mapFrom;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public AgendaModel createAgenda(AgendaModel agendaModel) {
        return mapFrom(agendaRepository.save(mapFrom(agendaModel)));
    }

    public List<AgendaModel> findAll() {
        return mapFrom(agendaRepository.findAll());
    }

    public AgendaModel findAgenda(Long agendaId) {
        return agendaRepository.findById(agendaId)
                .map(AgendaDomainMapper::mapFrom)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agenda " + agendaId.toString() + " not found."
                ));
    }
}
