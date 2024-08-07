package com.juliomesquita.application.aggregate_context.infra.controllers;

import com.juliomesquita.application.aggregate_context.domain.entities.SpeakerEntity;
import com.juliomesquita.application.aggregate_context.infra.persistence.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SpeakersController {
    private final SpeakerRepository speakerRepository;

    @QueryMapping
    List<SpeakerEntity> speakers(){
        return this.speakerRepository.findAll();
    }

    @QueryMapping
    Optional<SpeakerEntity> speaker(@Argument UUID id){
        return this.speakerRepository.findById(id);
    }
}
