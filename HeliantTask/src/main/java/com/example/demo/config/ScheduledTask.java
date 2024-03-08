package com.example.demo.config;

/**
 * @author Marko Kostic, on 2/28/2024
 */

import com.example.demo.service.StatistikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private StatistikaService statistikaService;

    @Scheduled(cron = "0 * * * * *") // 0 0 0 * * * -> Svaki dan u ponoc, radi testa stavicu na svakih minut
    public void prebrojPopunjeneFormulare() {
        statistikaService.countAndSaveStatistika();
    }
}
