package com.example.webX.backend.TechnicalAssignment.livescore.web.dto.response;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ScoreResponse {

    private String liveScorePublishDate;

    private String liveScoreTitle;

    private String liveScorelink;

    private String liveScoreDescription;

}
