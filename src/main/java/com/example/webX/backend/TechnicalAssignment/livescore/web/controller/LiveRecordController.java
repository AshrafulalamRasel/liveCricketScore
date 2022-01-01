package com.example.webX.backend.TechnicalAssignment.livescore.web.controller;

import com.example.webX.backend.TechnicalAssignment.common.constants.AccessApiConstant;
import com.example.webX.backend.TechnicalAssignment.livescore.constants.LiveCricketScore;
import com.example.webX.backend.TechnicalAssignment.livescore.service.LiveRecordService;
import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.response.ScoreResponsePagination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(LiveCricketScore.PUBLIC)
public class LiveRecordController {

    private final LiveRecordService liveRecordService;

@GetMapping("/live-score/fetchAll/{pageNo}")
    public ResponseEntity<ScoreResponsePagination> getAllLiveList(@PathVariable int pageNo) {
        return new ResponseEntity(liveRecordService.getAllLiveScoreListBy(pageNo), HttpStatus.OK);
    }

    @GetMapping("/searchAll")
    public ResponseEntity<ScoreResponsePagination> getAllLiveListBySearching(@RequestParam(value = "pageNo", required = true) int pageNo,
                                                                             @RequestParam(value = "liveScorePublishDate", required = false) String liveScorePublishDate,
                                                                             @RequestParam(value = "liveScoreTitle", required = false) String liveScoreTitle,
                                                                             @RequestParam(value = "liveScoreLink", required = false) String liveScoreLink,
                                                                             @RequestParam(value = "liveScoreDescription", required = false) String liveScoreDescription) {

        return new ResponseEntity(liveRecordService.getAllLiveScoreSearchingBy(pageNo, liveScorePublishDate,liveScoreTitle,liveScoreLink,liveScoreDescription), HttpStatus.OK);
    }
}
