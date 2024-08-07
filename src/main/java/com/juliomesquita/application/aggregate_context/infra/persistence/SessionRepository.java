package com.juliomesquita.application.aggregate_context.infra.persistence;

import com.juliomesquita.application.aggregate_context.domain.entities.SessionEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {
    Window<SessionEntity> findByEventId(UUID id, ScrollPosition position, Limit limit, Sort sort);
}
