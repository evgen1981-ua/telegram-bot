package com.githab.javarushcommunity.javarush_telegrambot.job;


import com.githab.javarushcommunity.javarush_telegrambot.service.FindNewArticleService;
import com.githab.javarushcommunity.javarush_telegrambot.service.FindNewArticleServiceIml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewArticleJob {

    private final FindNewArticleService findNewArticleService;
@Autowired
    public FindNewArticleJob(FindNewArticleServiceIml findNewArticleService) {
        this.findNewArticleService = findNewArticleService;
    }
    @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}")
    public void findNewArticles(){
        LocalDateTime start=LocalDateTime.now();
        log.info("Find new article job started.");
        findNewArticleService.findNewArticles();
        LocalDateTime end=LocalDateTime.now();
        log.info("find new article job finished. Took seconds:{}"
                ,end.toEpochSecond(ZoneOffset.UTC)-start.toEpochSecond(ZoneOffset.UTC));
    }

}
