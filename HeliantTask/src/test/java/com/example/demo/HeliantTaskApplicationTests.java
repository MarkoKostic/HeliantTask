package com.example.demo;

import com.example.demo.service.StatistikaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HeliantTaskApplicationTests {

    @Autowired
    StatistikaService statistikaService;

    @Test
    void contextLoads() {
    }

    @Test
    public void countAndSave() {
        statistikaService.countAndSaveStatistika();
    }

}
