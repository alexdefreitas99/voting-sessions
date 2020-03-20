package com.alexdefreitas.agenda.service;

import com.alexdefreitas.agenda.mapper.AgendaModelMapper;
import com.alexdefreitas.agenda.model.AgendaModel;
import com.alexdefreitas.agenda.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public AgendaModel postAgenda(AgendaModel agendaModel) {
        return AgendaModelMapper.mapFrom(agendaRepository.save(AgendaModelMapper.mapFrom(agendaModel)));
    }

    public List<AgendaModel> findAll() {
        return AgendaModelMapper.mapFrom(agendaRepository.findAll());
    }

    public AgendaModel findAgenda(Long agendaId) {
        return AgendaModelMapper
                .mapFrom(agendaRepository.findById(agendaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda not found")));
    }
}
