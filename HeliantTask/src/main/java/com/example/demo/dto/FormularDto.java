package com.example.demo.dto;

import com.example.demo.entity.PoljeEnum;
import com.example.demo.validation.DefaultValidationGroup;
import com.example.demo.validation.GroupWithOptionalFields;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marko Kostic, on 2/28/2024
 */
@Data
@GroupSequence({FormularDto.class, DefaultValidationGroup.class, GroupWithOptionalFields.class})
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormularDto {

    @NotNull
    @JsonProperty("naziv")
    private String naziv;
    @NotEmpty
    @JsonProperty("poljeDtoList")
    private List<PoljeDto> poljeDtoList = new ArrayList<>();

    //@JsonProperty("poljePopunjenoDto")
    //private PoljePopunjenoDto poljePopunjenoDto;

    @Data
    public static class PoljeDto {

        @JsonProperty("id")
        private Integer id; // dodato polje za identifikaciju, da li bismo znali da li je rec o insert-u ili update-u

        @NotNull
        @JsonProperty("naziv")
        private String naziv;
        @NotNull
        @JsonProperty("prikazniRedosled")
        private Integer prikazniRedosled;
        @NotNull
        @JsonProperty("tip")
        private PoljeEnum tip;

        @Null(groups = {GroupWithOptionalFields.class})
        @JsonProperty("vrednostBroj")
        private Double vrednostBroj;
        @Null(groups = {GroupWithOptionalFields.class})
        @JsonProperty("vrednostTekst")
        private String vrednostTekst;
    }

//    @Data
//    public static class PoljePopunjenoDto {
//        @Null(groups = {GroupWithOptionalFields.class})
//        @JsonProperty("vrednostBroj")
//        private Double vrednostBroj;
//        @Null(groups = {GroupWithOptionalFields.class})
//        @JsonProperty("vrednostTekst")
//        private String vrednostTekst;
//    }
}
