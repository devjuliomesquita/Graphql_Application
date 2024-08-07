package com.juliomesquita.application.aggregate_context.domain.entities;

import com.juliomesquita.application.aggregate_context.domain.enums.LevelType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_sessions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "session_id", nullable = false)
    private UUID id;

    @Column(name = "session_title")
    private String title;

    @Column(name = "session_description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "session_level")
    private LevelType level;

    @ManyToMany
    @JoinTable(
            name = "tb_session_tags",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags;

    @ManyToOne
    @JoinColumn(name = "session_event_id", referencedColumnName = "event_id")
    private EventEntity event;
}
