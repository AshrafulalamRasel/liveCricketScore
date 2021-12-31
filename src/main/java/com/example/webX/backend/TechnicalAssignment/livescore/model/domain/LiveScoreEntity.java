package com.example.webX.backend.TechnicalAssignment.livescore.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LIVE_SCORE_DB")
public class LiveScoreEntity {

    @Id
    private String guid;

    @Column(nullable = false)
    private String liveScorePublishDate;

    @Column(nullable = false)
    private String liveScoreTitle;

    @Column(nullable = false)
    private String liveScoreLink;

    @Column(nullable = false)
    private String liveScoreDescription;

}
