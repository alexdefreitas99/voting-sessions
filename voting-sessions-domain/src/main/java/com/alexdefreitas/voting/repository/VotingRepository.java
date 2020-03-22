package com.alexdefreitas.voting.repository;

import com.alexdefreitas.voting.model.entity.VotingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingRepository extends CrudRepository<VotingEntity, Long> {
    Boolean existsByAssociatedCpfAndSessionId(String associatedCpf, Long sessionId);

    List<VotingEntity> findBySessionAgendaId(Long agendaId);
}
