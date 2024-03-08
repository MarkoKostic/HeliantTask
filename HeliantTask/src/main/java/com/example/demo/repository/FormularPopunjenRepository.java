package com.example.demo.repository;

import com.example.demo.entity.Formular;
import com.example.demo.entity.FormularPopunjen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marko Kostic, on 2/27/2024
 */
@Repository
public interface FormularPopunjenRepository extends JpaRepository<FormularPopunjen, Long> {

    void deleteByFormular(Formular formular);

    List<FormularPopunjen> findByFormular(Formular formular);

    // Integer countByDatumPopunjavanjaBetween(LocalDateTime startDate, LocalDateTime endDate);
}
