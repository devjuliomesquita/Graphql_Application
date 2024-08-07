package com.juliomesquita.application.aggregate_context.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_tags")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tag_id", nullable = false)
    private UUID id;

    @Column(name = "tag_name")
    private String name;
}
