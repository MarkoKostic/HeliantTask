package com.example.demo.config;

/**
 * @author Marko Kostic, on 2/28/2024
 */

import com.example.demo.repository.FormularPopunjenRepository;
import com.example.demo.service.StatistikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private FormularPopunjenRepository formularPopunjenRepository;

    @Autowired
    private StatistikaService statistikaService;

//    @Scheduled(cron = "0 0 0 * * ?")
//    public void prebrojPopunjeneFormulare() {
//        LocalDate danas = LocalDate.now();
//        LocalDate juce = danas.minusDays(1);
//        Date datum = Date.from(juce.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//        Integer brojPopunjenihFormulara = formularPopunjenRepository.countByDatumPopunjavanjaBetween(juce.atStartOfDay(), danas.atStartOfDay());
//        statistikaService.saveStatistika(datum, brojPopunjenihFormulara);
//    }
}
