package com.example.demo.service.impl;

import com.example.demo.entity.Statistika;
import com.example.demo.repository.StatistikaRepository;
import com.example.demo.service.StatistikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Marko Kostic, on 2/28/2024
 */
@Service
@Transactional
public class StatistikaServiceImpl implements StatistikaService {

    @Autowired
    private StatistikaRepository statistikaRepository;

    @Override
    public void saveStatistika(Date datum, Integer brojPopunjenihFormulara) {
        Statistika statistika = statistikaRepository.findByDatum(datum).orElseGet(() -> new Statistika(datum, 0));
        statistika.setBrojPopunjenihFormulara(statistika.getBrojPopunjenihFormulara() + brojPopunjenihFormulara);
        statistikaRepository.save(statistika);
    }
}
