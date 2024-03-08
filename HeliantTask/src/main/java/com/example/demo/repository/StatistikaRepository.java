package com.example.demo.repository;

import com.example.demo.entity.Statistika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
 * @author Marko Kostic, on 2/28/2024
 */
@Repository
public interface StatistikaRepository extends JpaRepository<Statistika, Long> {
    Optional<Statistika> findByDatum(Date date);


}
