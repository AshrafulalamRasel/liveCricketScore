package com.example.webX.backend.TechnicalAssignment.livescore.service.serviceImplementation;

import com.example.webX.backend.TechnicalAssignment.common.Utils.DataBindConverter;
import com.example.webX.backend.TechnicalAssignment.livescore.model.domain.LiveScoreEntity;
import com.example.webX.backend.TechnicalAssignment.livescore.model.repository.LiveScoreRepository;
import com.example.webX.backend.TechnicalAssignment.livescore.service.LiveRecordService;
import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.request.Item;
import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.request.ScoreRequest;
import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.response.ScoreResponse;
import com.example.webX.backend.TechnicalAssignment.livescore.web.dto.response.ScoreResponsePagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LiveRecordServiceImpl implements LiveRecordService {

    private final DataBindConverter dataBindConverter;
    private final LiveScoreRepository liveScoreRepository;


    @Override
    public ResponseEntity<String> getData() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://static.cricinfo.com/rss/livescores.xml";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        ScoreRequest scoreRequest = dataBindConverter.xmlToDto(result.getBody());
        createLiveScore(scoreRequest);
        return result;

    }

    @Override
    public ResponseEntity<LiveScoreEntity> createLiveScore(ScoreRequest scoreRequest) {

        for (Item item : scoreRequest.getChannel().getItem()) {
            Optional<LiveScoreEntity> optionalLiveScoreEntity = liveScoreRepository.findById(item.getGuid());

            if (!optionalLiveScoreEntity.isPresent()) {
                LiveScoreEntity liveScoreEntity = new LiveScoreEntity();
                liveScoreEntity.setLiveScorePublishDate(scoreRequest.getChannel().getPubDate());
                liveScoreEntity.setGuid(item.getGuid());
                liveScoreEntity.setLiveScoreTitle(item.getTitle());
                liveScoreEntity.setLiveScoreDescription(item.getDescription());
                liveScoreEntity.setLiveScoreLink(item.getLink());
                liveScoreRepository.saveAndFlush(liveScoreEntity);
            } else {
                LiveScoreEntity liveScoreEntity = optionalLiveScoreEntity.get();

                liveScoreEntity.setLiveScoreTitle(item.getTitle());
                liveScoreEntity.setLiveScoreDescription(item.getDescription());
                liveScoreEntity.setLiveScoreLink(item.getLink());
                liveScoreRepository.save(liveScoreEntity);
            }
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ScoreResponsePagination getAllLiveScoreListBy(int pageNo) {

        List<ScoreResponse> scoreResponseList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNo, 8);

        Page<LiveScoreEntity> liveScoreEntityList = liveScoreRepository.findAll(pageable);

        int pageNumber = liveScoreEntityList.getNumber();
        int pageSize = liveScoreEntityList.getSize();
        int totalPage = liveScoreEntityList.getTotalPages();
        Long totalElement = liveScoreEntityList.getTotalElements();

        liveScoreEntityList.forEach(scoreList -> {

            ScoreResponse scoreResponse = new ScoreResponse();
            scoreResponse.setLiveScoreTitle(scoreList.getLiveScoreTitle());
            scoreResponse.setLiveScoreDescription(scoreList.getLiveScoreDescription());
            scoreResponse.setLiveScorelink(scoreList.getLiveScoreLink());
            scoreResponse.setLiveScorePublishDate(scoreList.getLiveScorePublishDate());
            scoreResponseList.add(scoreResponse);

        });

        return ScoreResponsePagination.builder()
                .page(pageNumber)
                .size(pageSize)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .scoreResponseList(scoreResponseList)
                .build();
    }

    @Override
    public ScoreResponsePagination getAllLiveScoreSearchingBy(int pageNo, String liveScorePublishDate, String liveScoreTitle, String liveScoreLink, String liveScoreDescription) {

        List<ScoreResponse> scoreResponseList = new ArrayList<>();

        LiveScoreEntity scoreResponse = LiveScoreEntity.builder()
                .liveScorePublishDate(liveScorePublishDate)
                .liveScoreTitle(liveScoreTitle)
                .liveScoreLink(liveScoreLink)
                .liveScoreDescription(liveScoreDescription)
                .build();


        //Sorting the data
        Sort sort = Sort.by(Sort.Direction.ASC, "liveScoreDescription");

        //For advance searching
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("liveScoreDescription", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("liveScoreTitle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());


        Pageable pageable = PageRequest.of(pageNo, 8, sort);


        //Getting the list of product with example filtering
        Page<LiveScoreEntity> liveScoreEntityPage = liveScoreRepository.findAll(Example.of(scoreResponse,matcher), pageable);

        int pageNumber = liveScoreEntityPage.getNumber();
        int pageSize = liveScoreEntityPage.getSize();
        int totalPage = liveScoreEntityPage.getTotalPages();
        Long totalElement = liveScoreEntityPage.getTotalElements();

        liveScoreEntityPage.forEach(scoreList -> {

            ScoreResponse scoreResponseData = new ScoreResponse();
            scoreResponseData.setLiveScoreTitle(scoreList.getLiveScoreTitle());
            scoreResponseData.setLiveScoreDescription(scoreList.getLiveScoreDescription());
            scoreResponseData.setLiveScorelink(scoreList.getLiveScoreLink());
            scoreResponseData.setLiveScorePublishDate(scoreList.getLiveScorePublishDate());
            scoreResponseList.add(scoreResponseData);

        });

        return ScoreResponsePagination.builder()
                .page(pageNumber)
                .size(pageSize)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .scoreResponseList(scoreResponseList)
                .build();

    }


}
