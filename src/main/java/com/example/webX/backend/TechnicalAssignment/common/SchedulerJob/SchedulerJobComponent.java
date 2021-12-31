package com.example.webX.backend.TechnicalAssignment.common.SchedulerJob;

import com.example.webX.backend.TechnicalAssignment.livescore.service.serviceImplementation.LiveRecordServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@AllArgsConstructor
@Component
public class SchedulerJobComponent {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerJobComponent.class);
    private final LiveRecordServiceImpl liveRecordService;

    @Scheduled(cron="*/5 * * * * ?",zone = "GMT+6:00")
    public void printMessage() throws IOException, URISyntaxException {
        liveRecordService.getData();
        LOG.info("Live Score Record Pushing");
        System.out.println("Scheduler is calling Successfully!!");
    }
}
