package com.juliomesquita.application.aggregate_context.infra.controllers;

import com.juliomesquita.application.aggregate_context.domain.entities.EventEntity;
import com.juliomesquita.application.aggregate_context.infra.persistence.EventRepository;
import com.juliomesquita.application.aggregate_context.infra.persistence.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepository;
    private final SessionRepository sessionRepository;

    @QueryMapping
    List<EventEntity> events() {
        return this.eventRepository.findAll();
    }

    @QueryMapping
    Optional<EventEntity> event(@Argument UUID id) {
        return this.eventRepository.findById(id);
    }
}
