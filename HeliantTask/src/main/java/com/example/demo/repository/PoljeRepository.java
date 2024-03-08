package com.example.demo.repository;

import com.example.demo.entity.Formular;
import com.example.demo.entity.Polje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marko Kostic, on 2/27/2024
 */
@Repository
public interface PoljeRepository extends JpaRepository<Polje, Long> {

    List<Polje> findByFormular(Formular formular);
}
