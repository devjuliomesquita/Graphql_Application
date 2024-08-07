package com.juliomesquita.application.aggregate_context.infra.controllers;

import com.juliomesquita.application.aggregate_context.domain.entities.SessionEntity;
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
public class SessionController {
    private final SessionRepository sessionRepository;

    @QueryMapping
    List<SessionEntity> sessions(){
        return this.sessionRepository.findAll();
    }

    @QueryMapping
    Optional<SessionEntity> session(@Argument UUID id){
        return this.sessionRepository.findById(id);
    }
}
