package com.juliomesquita.application.aggregate_context.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id", nullable = false)
    private UUID id;

    @Column(name = "event_name")
    private String name;

    @Column(name = "event_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "event_start_date")
    private LocalDate startDate;

    @Column(name = "event_end_date")
    private LocalDate endDate;

    @Column(name = "event_cfp_start_date")
    private LocalDate cfpStartDate;

    @Column(name = "event_cfp_end_date")
    private LocalDate cfpEndDate;

    @Column(name = "event_location")
    private String location;

    @Column(name = "event_website")
    private String website;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<SessionEntity> sessions = new HashSet<>();
}
