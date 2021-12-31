package com.example.webX.backend.TechnicalAssignment.common.Utils;

import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.request.ScoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class DataBindConverter {

    public ScoreRequest xmlToDto(String inputxml) {

        ScoreRequest scoreRequest = new ScoreRequest();
        try {
            ObjectMapper objectMapper = new XmlMapper();
            scoreRequest = objectMapper.readValue(inputxml, ScoreRequest.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreRequest;
    }
}
