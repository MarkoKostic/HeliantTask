package com.example.demo.repository;

import com.example.demo.entity.Polje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marko Kostic, on 2/27/2024
 */
@Repository
public interface PoljeRepository extends JpaRepository<Polje, Long> {

}
