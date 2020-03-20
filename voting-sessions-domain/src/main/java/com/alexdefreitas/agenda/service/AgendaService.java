package com.alexdefreitas.agenda.service;

import com.alexdefreitas.agenda.mapper.AgendaMapper;
import com.alexdefreitas.agenda.model.AgendaModel;
//import com.alexdefreitas.agenda.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

//    @Autowired
//    private AgendaRepository agendaRepository;

    public AgendaModel postAgenda(AgendaModel agendaModel) {
        System.out.println(agendaModel.getSubject());
        return new AgendaModel();
//        return AgendaMapper.mapFrom(agendaRepository.save(AgendaMapper.mapFrom(agendaModel)));
    }
}
