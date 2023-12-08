package com.example.rickandmortyapi.gobal.config;

import com.example.rickandmortyapi.infraestructure.api.consumers.ApiConsumer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class CachingCleaner {

    private final CacheManager cacheManager;
    private final ApiConsumer apiConsumer;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        apiConsumer.getAllCharacters();
        log.info("Cache loaded");
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateCharacterCaching() {
        log.info("Cleaning cache");
        cacheManager.getCache("characters").clear();
        init();
    }
}
