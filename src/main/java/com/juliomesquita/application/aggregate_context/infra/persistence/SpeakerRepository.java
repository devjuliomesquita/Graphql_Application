package com.juliomesquita.application.aggregate_context.infra.persistence;

import com.juliomesquita.application.aggregate_context.domain.entities.SpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpeakerRepository extends JpaRepository<SpeakerEntity, UUID> {
}
