package com.juliomesquita.application.aggregate_context.infra.controllers;

import com.juliomesquita.application.aggregate_context.domain.entities.EventEntity;
import com.juliomesquita.application.aggregate_context.domain.entities.SessionEntity;
import com.juliomesquita.application.aggregate_context.infra.persistence.EventRepository;
import com.juliomesquita.application.aggregate_context.infra.persistence.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
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

    @SchemaMapping
    Window<SessionEntity> sessions(EventEntity event, ScrollSubrange subrange){
        ScrollPosition position = subrange.position().orElse(ScrollPosition.offset());
        Limit limit = Limit.of(subrange.count().orElse(10));
        Sort sort = Sort.by("title").ascending();

        return this.sessionRepository.findByEventId(event.getId(), position, limit, sort);
    }
}
