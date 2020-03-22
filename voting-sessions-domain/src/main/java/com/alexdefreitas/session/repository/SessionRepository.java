package com.alexdefreitas.session.repository;

import com.alexdefreitas.session.model.entity.SessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends CrudRepository<SessionEntity, Long> {
    Optional<SessionEntity> findByIdAndAgendaId(Long sessionId, Long agendaId);
}
