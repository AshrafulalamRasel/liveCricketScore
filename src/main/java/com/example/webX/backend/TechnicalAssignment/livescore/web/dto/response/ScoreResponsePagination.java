package com.example.webX.backend.TechnicalAssignment.livescore.web.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ScoreResponsePagination {

    private int page;
    private int size;
    private int totalPage;
    private Long totalElement;
    private List<ScoreResponse> scoreResponseList;

}
