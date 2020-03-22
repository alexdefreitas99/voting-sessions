package com.alexdefreitas.agenda.repository;

import com.alexdefreitas.agenda.model.entity.AgendaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends CrudRepository<AgendaEntity, Long> {
}
