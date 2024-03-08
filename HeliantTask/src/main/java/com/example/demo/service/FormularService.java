package com.example.demo.service;

import com.example.demo.dto.FormularDto;
import com.example.demo.entity.Formular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Marko Kostic, on 2/27/2024
 */
public interface FormularService {

    Page<Formular> getAllFormulari(Pageable pageable);

    Optional<Formular> getFormularById(Integer id);

    FormularDto saveFormular(FormularDto formularDto);

    FormularDto updateFormular(Integer id, FormularDto updatedFormularDto);

    void deleteFormular(Integer id);
}
