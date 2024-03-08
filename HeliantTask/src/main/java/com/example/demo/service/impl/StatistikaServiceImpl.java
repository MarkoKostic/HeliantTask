package com.example.demo.service.impl;

import com.example.demo.entity.Statistika;
import com.example.demo.repository.FormularPopunjenRepository;
import com.example.demo.repository.StatistikaRepository;
import com.example.demo.service.StatistikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Marko Kostic, on 2/28/2024
 */
@Service
@Transactional
public class StatistikaServiceImpl implements StatistikaService {

    @Autowired
    private FormularPopunjenRepository formularPopunjenRepository;

    @Autowired
    private StatistikaRepository statistikaRepository;

    @Override
    public void countAndSaveStatistika() {
        LocalDate danas = LocalDate.now();
        LocalDate juce = danas.minusDays(1);
        Date datum = Date.from(juce.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Integer brojPopunjenihFormulara = formularPopunjenRepository.countByVremeKreiranjaBetween(juce.atStartOfDay(), danas.atStartOfDay());

        Statistika statistika = new Statistika();
        statistika.setDatum(datum);
        statistika.setBrojPopunjenihFormulara(brojPopunjenihFormulara);
        statistikaRepository.save(statistika);
    }
}
