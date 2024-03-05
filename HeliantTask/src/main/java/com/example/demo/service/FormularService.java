package com.example.demo.service;

import com.example.demo.dto.FormularDto;
import com.example.demo.entity.Formular;

import java.util.List;
import java.util.Optional;

/**
 * @author Marko Kostic, on 2/27/2024
 */
public interface FormularService {

    List<Formular> getAllFormulari();

    Optional<Formular> getFormularById(Integer id);

    FormularDto saveFormular(FormularDto formularDto);

    Formular updateFormular(Integer id, Formular updatedFormular);

    void deleteFormular(Integer id);
}
