package com.example.demo.repository;

import com.example.demo.entity.PoljePopunjeno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marko Kostic, on 2/28/2024
 */
@Repository
public interface PoljePopunjenoRepository extends JpaRepository<PoljePopunjeno, Long> {

}
