package com.github.lowkkid.lodgecore.common.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class MockDataJob {

    private final MockDataService mockDataService;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0 12 * * ?")
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
