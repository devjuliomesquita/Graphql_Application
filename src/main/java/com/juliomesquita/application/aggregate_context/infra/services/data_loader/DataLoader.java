package com.juliomesquita.application.aggregate_context.infra.services.data_loader;

import com.juliomesquita.application.aggregate_context.domain.entities.EventEntity;
import com.juliomesquita.application.aggregate_context.domain.entities.SessionEntity;
import com.juliomesquita.application.aggregate_context.domain.entities.SpeakerEntity;
import com.juliomesquita.application.aggregate_context.domain.entities.TagEntity;
import com.juliomesquita.application.aggregate_context.domain.enums.GenderType;
import com.juliomesquita.application.aggregate_context.domain.enums.LevelType;
import com.juliomesquita.application.aggregate_context.infra.persistence.EventRepository;
import com.juliomesquita.application.aggregate_context.infra.persistence.SessionRepository;
import com.juliomesquita.application.aggregate_context.infra.persistence.SpeakerRepository;
import com.juliomesquita.application.aggregate_context.infra.persistence.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final Faker faker;
    private final EventRepository eventRepository;
    private final SessionRepository sessionRepository;
    private final TagRepository tagRepository;
    private final SpeakerRepository speakerRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (this.tagRepository.count() == 0) {
            TagEntity tag1 = TagEntity.builder().name("Spring Boot").build();
            TagEntity tag2 = TagEntity.builder().name("Spring Cloud").build();
            TagEntity tag3 = TagEntity.builder().name("Spring StateMachine").build();
            this.tagRepository.saveAll(List.of(tag1, tag2, tag3));
        }

        if (this.eventRepository.count() == 0) {
            EventEntity event = EventEntity.builder()
                    .name("Javax CE")
                    .description("Descrição")
                    .startDate(LocalDate.of(2024, 8, 21))
                    .endDate(LocalDate.of(2024, 8, 24))
                    .location(this.faker.location().publicSpace())
                    .website(this.faker.internet().url())
                    .cfpStartDate(LocalDate.now().minusDays(20))
                    .cfpEndDate(LocalDate.now().minusDays(10))
                    .build();
            EventEntity eventSaved = this.eventRepository.save(event);

            List<SpeakerEntity> speakers = new ArrayList<>();
            for (int i = 0; i <= 20; i++) {
                speakers.add(SpeakerEntity.builder()
                        .name(this.faker.name().fullName())
                        .title(this.faker.name().title())
                        .company(this.faker.company().name())
                        .gender(GenderType.values()[this.faker.number().numberBetween(0, GenderType.values().length)])
                        .country(this.faker.address().country())
                        .email(this.faker.internet().emailAddress())
                        .phoneNumber(this.faker.phoneNumber().phoneNumber())
                        .linkedin(this.faker.twitter().userName())
                        .build());
            }
            this.speakerRepository.saveAll(speakers);

            List<SessionEntity> sessions = new ArrayList<>();
            for (int i = 0; i <= 20; i++) {
                sessions.add(SessionEntity.builder()
                        .title(this.faker.book().title())
                        .description(this.faker.lorem().paragraph())
                        .level(LevelType.values()[this.faker.number().numberBetween(0, LevelType.values().length)])
                        .tags(Set.of(this.tagRepository.findAll().toArray(new TagEntity[0])))
                        .event(eventSaved)

                        .build());
            }
            this.sessionRepository.saveAll(sessions);

        }
    }
}
