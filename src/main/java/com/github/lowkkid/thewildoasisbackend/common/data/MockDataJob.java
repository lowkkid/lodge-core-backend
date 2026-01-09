package com.github.lowkkid.thewildoasisbackend.common.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class MockDataJob {

    private final MockDataService mockDataService;

    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(fixedDelay = 300000)
    public void execute() {
        log.info("Starting daily mock data loading...");
        try {
            mockDataService.loadData();
            log.info("Daily mock data successfully loaded");
        } catch (Exception e) {
            log.error("Error while loading data", e);
        }
    }

}
