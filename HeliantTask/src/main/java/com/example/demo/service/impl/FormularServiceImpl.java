package com.example.demo.service.impl;

import com.example.demo.converter.FormularConverter;
import com.example.demo.dto.FormularDto;
import com.example.demo.entity.Formular;
import com.example.demo.entity.FormularPopunjen;
import com.example.demo.entity.Polje;
import com.example.demo.entity.PoljePopunjeno;
import com.example.demo.repository.FormularPopunjenRepository;
import com.example.demo.repository.FormularRepository;
import com.example.demo.repository.PoljePopunjenoRepository;
import com.example.demo.repository.PoljeRepository;
import com.example.demo.service.FormularService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Marko Kostic, on 2/27/2024
 */
@Service
@Transactional
public class FormularServiceImpl implements FormularService {

    @Autowired
    private FormularRepository formularRepository;

    @Autowired
    private FormularPopunjenRepository formularPopunjenRepository;

    @Autowired
    private PoljeRepository poljeRepository;

    @Autowired
    private PoljePopunjenoRepository poljePopunjenoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Formular> getAllFormulari() {
        return formularRepository.findAll();
    }

    @Override
    public Optional<Formular> getFormularById(Integer id) {
        return formularRepository.findById(Long.valueOf(id));
    }

    @Override
    public FormularDto saveFormular(FormularDto formularDto) {
        Formular formularEntity = FormularConverter.convertToEntity(formularDto);
        formularEntity = formularRepository.save(formularEntity);

        // save then FormularPopunjen
        FormularPopunjen formularPopunjenEntity = new FormularPopunjen();
        formularPopunjenEntity.setFormular(formularEntity);
        FormularPopunjen savedFormularPopunjen = formularPopunjenRepository.save(formularPopunjenEntity);

        // save Polje
        if (formularDto.getPoljeDtoList() != null) {
            for (FormularDto.PoljeDto poljeDto : formularDto.getPoljeDtoList()) {
                Polje poljeEntity = new Polje();
                poljeEntity.setFormular(formularEntity);
                poljeEntity.setNaziv(poljeDto.getNaziv());
                poljeEntity.setPrikazniRedosled(poljeDto.getPrikazniRedosled());
                poljeEntity.setTip(poljeDto.getTip());
                Polje savedPolje = poljeRepository.save(poljeEntity);

                PoljePopunjeno poljePopunjenoEntity = FormularConverter.convertToPoljePopunjenoEntity(poljeDto);
                poljePopunjenoEntity.setFormularPopunjen(savedFormularPopunjen);
                poljePopunjenoEntity.setPolje(savedPolje);
                poljePopunjenoRepository.save(poljePopunjenoEntity);
            }
        }

        return formularDto;
    }

    private Formular mapirajFormularDtoNaFormular(FormularDto formularDto) {
        return objectMapper.convertValue(formularDto, Formular.class);
    }

    private FormularPopunjen mapirajFormularNaFormularPopunjen(Formular formular) {
        FormularPopunjen formularPopunjen = new FormularPopunjen();
        formularPopunjen.setFormular(formular);
        return formularPopunjen;
    }

    /**
     * private void mapirajIPersistujPolja(List<FormularDto.PoljeDto> poljeDtoList, Formular formular, FormularPopunjen formularPopunjen) {
     * for (FormularDto.PoljeDto poljeDto : poljeDtoList) {
     * // Mapiranje Polje
     * Polje polje = mapirajPoljeDtoNaPolje(poljeDto, formular);
     * poljeRepository.save(polje);
     * <p>
     * // Mapiranje PoljePopunjeno
     * FormularDto.PoljePopunjenoDto poljePopunjenoDto = new FormularDto.PoljePopunjenoDto();
     * // Postavite vrednosti za poljePopunjenoDto ako su dostupne
     * PoljePopunjeno poljePopunjeno = mapirajPoljePopunjenoDtoNaPoljePopunjeno(poljePopunjenoDto, polje, formularPopunjen);
     * poljePopunjenoRepository.save(poljePopunjeno);
     * }
     * }
     */

    private Polje mapirajPoljeDtoNaPolje(FormularDto.PoljeDto poljeDto, Formular formular) {
        Polje polje = objectMapper.convertValue(poljeDto, Polje.class);
        polje.setFormular(formular);
        polje.setNaziv(poljeDto.getNaziv());
        polje.setPrikazniRedosled(poljeDto.getPrikazniRedosled());
        polje.setTip(poljeDto.getTip());
        return polje;
    }

    /**
     * private PoljePopunjeno mapirajPoljePopunjenoDtoNaPoljePopunjeno(FormularDto.PoljePopunjenoDto poljePopunjenoDto, Polje polje, FormularPopunjen formularPopunjen) {
     * PoljePopunjeno poljePopunjeno = objectMapper.convertValue(poljePopunjenoDto, PoljePopunjeno.class);
     * poljePopunjeno.setPolje(polje);
     * poljePopunjeno.setFormularPopunjen(formularPopunjen);
     * poljePopunjeno.setVrednostTekst(! poljePopunjenoDto.getVrednostTekst().isEmpty() ? poljePopunjenoDto.getVrednostTekst() : "");
     * poljePopunjeno.setVrednostBroj(poljePopunjenoDto.getVrednostBroj() != null ? poljePopunjenoDto.getVrednostBroj() : null);
     * return poljePopunjeno;
     * }
     */

    @Override
    public FormularDto updateFormular(Integer id, FormularDto formularDto) {
        // Provera postojanja formulara sa datim ID
        Optional<Formular> existingFormularOptional = formularRepository.findById(Long.valueOf(id));
        if (existingFormularOptional.isEmpty()) {
            throw new EntityNotFoundException("Formular not found with id: " + id);
        }

        Formular existingFormular = existingFormularOptional.get();
        // Ažuriranje informacija o formularu
        existingFormular.setNaziv(formularDto.getNaziv());

        // Ažuriranje informacija o poljima
        for (FormularDto.PoljeDto poljeDto : formularDto.getPoljeDtoList()) {
            // Ako postoji PoljePopunjeno sa datim identifikatorom, ažuriraj ga
            if (poljeDto.getId() != null) {
                Optional<PoljePopunjeno> existingPoljePopunjenoOptional = poljePopunjenoRepository.findByIdentifier(poljeDto.getId()); // ovde je greska, moram da vidim za identifier, nije dobro ovako
                if (existingPoljePopunjenoOptional.isEmpty()) {
                    throw new EntityNotFoundException("PoljePopunjeno not found with identifier: " + poljeDto.getId());
                }

                PoljePopunjeno existingPoljePopunjeno = existingPoljePopunjenoOptional.get();
                existingPoljePopunjeno.setVrednostBroj(poljeDto.getVrednostBroj());
                existingPoljePopunjeno.setVrednostTekst(poljeDto.getVrednostTekst());
                // Ažuriranje informacija u bazi
                poljePopunjenoRepository.save(existingPoljePopunjeno);
            } else {
                // Ako ne postoji identifikator, onda dodajemo novo PoljePopunjeno
                PoljePopunjeno newPoljePopunjeno = FormularConverter.convertToPoljePopunjenoEntity(poljeDto);
                poljePopunjenoRepository.save(newPoljePopunjeno);
            }
        }

        // Čuvamo ažurirani formular
        formularRepository.save(existingFormular);

        return formularDto;
    }

    @Override
    public void deleteFormular(Integer id) {
        formularRepository.deleteById(Long.valueOf(id));
    }
}
