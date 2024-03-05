package com.example.demo.converter;

import com.example.demo.dto.FormularDto;
import com.example.demo.entity.Formular;
import com.example.demo.entity.PoljeEnum;
import com.example.demo.entity.PoljePopunjeno;
import org.springframework.stereotype.Component;

/**
 * @author Marko Kostic, on 2/29/2024
 */
@Component
public class FormularConverter {

    public static Formular convertToEntity(FormularDto formularDto) {
        Formular formular = new Formular();
        formular.setNaziv(formularDto.getNaziv());
        return formular;
    }

    public static PoljePopunjeno convertToPoljePopunjenoEntity(FormularDto.PoljeDto poljeDto) {
        PoljePopunjeno poljePopunjeno = new PoljePopunjeno();
        if (poljeDto.getTip().equals(PoljeEnum.TEKST))
            poljePopunjeno.setVrednostTekst(poljeDto.getVrednostTekst());
        else
            poljePopunjeno.setVrednostBroj(poljeDto.getVrednostBroj());
        return poljePopunjeno;
    }
}
