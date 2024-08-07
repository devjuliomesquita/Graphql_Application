package com.juliomesquita.application.aggregate_context.domain.entities;

import com.juliomesquita.application.aggregate_context.domain.enums.GenderType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_speakers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SpeakerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "speaker_id", nullable = false)
    private UUID id;

    @Column(name = "speaker_name")
    private String name;

    @Column(name = "speaker_title")
    private String title;

    @Column(name = "speaker_company")
    private String company;

    @Enumerated(EnumType.STRING)
    @Column(name = "speaker_gender", length = 10)
    private GenderType gender;

    @Column(name = "speaker_country")
    private String country;

    @Column(name = "speaker_email")
    private String email;

    @Column(name = "speaker_phone_number")
    private String phoneNumber;

    @Column(name = "speaker_linkedin")
    private String linkedin;
}
