package com.example.demo.service.impl;

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
    public void saveFormular(FormularDto formularDto) {

        // Mapiranje FormularDto na Formular
        Formular formular = mapirajFormularDtoNaFormular(formularDto);

        // Čuvanje Formulara u bazi podataka
        formularRepository.save(formular);

        // Mapiranje i čuvanje FormularPopunjen
        FormularPopunjen formularPopunjen = mapirajFormularNaFormularPopunjen(formular);
        formularPopunjenRepository.save(formularPopunjen);

        // Mapiranje i čuvanje Polja i PoljaPopunjeno
        mapirajIPersistujPolja(formularDto.getPoljeDtoList(), formular, formularPopunjen);
    }

    private Formular mapirajFormularDtoNaFormular(FormularDto formularDto) {
        return objectMapper.convertValue(formularDto, Formular.class);
    }

    private FormularPopunjen mapirajFormularNaFormularPopunjen(Formular formular) {
        FormularPopunjen formularPopunjen = new FormularPopunjen();
        formularPopunjen.setFormular(formular);
        return formularPopunjen;
    }

    private void mapirajIPersistujPolja(List<FormularDto.PoljeDto> poljeDtoList, Formular formular, FormularPopunjen formularPopunjen) {
        for (FormularDto.PoljeDto poljeDto : poljeDtoList) {
            // Mapiranje Polje
            Polje polje = mapirajPoljeDtoNaPolje(poljeDto, formular);
            poljeRepository.save(polje);

            // Mapiranje PoljePopunjeno
            FormularDto.PoljePopunjenoDto poljePopunjenoDto = new FormularDto.PoljePopunjenoDto();
            // Postavite vrednosti za poljePopunjenoDto ako su dostupne
            PoljePopunjeno poljePopunjeno = mapirajPoljePopunjenoDtoNaPoljePopunjeno(poljePopunjenoDto, polje, formularPopunjen);
            poljePopunjenoRepository.save(poljePopunjeno);
        }
    }

    private Polje mapirajPoljeDtoNaPolje(FormularDto.PoljeDto poljeDto, Formular formular) {
        Polje polje = objectMapper.convertValue(poljeDto, Polje.class);
        polje.setFormular(formular);
        polje.setNaziv(poljeDto.getNaziv());
        polje.setPrikazniRedosled(poljeDto.getPrikazniRedosled());
        polje.setTip(poljeDto.getTip());
        return polje;
    }

    private PoljePopunjeno mapirajPoljePopunjenoDtoNaPoljePopunjeno(FormularDto.PoljePopunjenoDto poljePopunjenoDto, Polje polje, FormularPopunjen formularPopunjen) {
        PoljePopunjeno poljePopunjeno = objectMapper.convertValue(poljePopunjenoDto, PoljePopunjeno.class);
        poljePopunjeno.setPolje(polje);
        poljePopunjeno.setFormularPopunjen(formularPopunjen);
        poljePopunjeno.setVrednostTekst(! poljePopunjenoDto.getVrednostTekst().isEmpty() ? poljePopunjenoDto.getVrednostTekst() : "");
        poljePopunjeno.setVrednostBroj(poljePopunjenoDto.getVrednostBroj() != null ? poljePopunjenoDto.getVrednostBroj() : null);
        return poljePopunjeno;
    }

    @Override
    public Formular updateFormular(Integer id, Formular updatedFormular) {
        Optional<Formular> formular = formularRepository.findById(Long.valueOf(id));

        if (formular.isPresent()) {
            Formular existingFormular = formular.get();
            existingFormular.setNaziv(updatedFormular.getNaziv());
            return formularRepository.save(existingFormular);
        }
        return null; // if there is no formular with given ID, return null
    }

    @Override
    public void deleteFormular(Integer id) {
        formularRepository.deleteById(Long.valueOf(id));
    }
}
