package com.example.webX.backend.TechnicalAssignment.livescore.service;

import com.example.webX.backend.TechnicalAssignment.livescore.model.domain.LiveScoreEntity;
import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.request.ScoreRequest;
import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.response.ScoreResponsePagination;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public interface LiveRecordService {

    ResponseEntity<String> getBasedXmlDataSet() throws URISyntaxException;

    ResponseEntity<LiveScoreEntity> createLiveScore(ScoreRequest scoreRequest);

    ScoreResponsePagination getAllLiveScoreListBy(int pageNo);

    ScoreResponsePagination getAllLiveScoreSearchingBy(int pageNo,
                                                       String body,
                                                       String liveScoreTitle,
                                                       String liveScoreLink,
                                                       String liveScoreDescription);

}
