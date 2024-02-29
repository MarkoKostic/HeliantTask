package com.example.demo.controller;

import com.example.demo.dto.FormularDto;
import com.example.demo.entity.Formular;
import com.example.demo.service.FormularService;
import com.example.demo.validation.GroupWithOptionalFields;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Validated({GroupWithOptionalFields.class})
public class AdminController {

    @Autowired
    private FormularService formularService;

    @PostMapping("/createFormular")
    public void createFormular(@RequestBody @Valid FormularDto formularDto) {
        formularService.saveFormular(formularDto);
    }

    // createFormulars

    // (FormularsDto lista formulara)

    @GetMapping("/formulari")
    public List<Formular> getAllFormulari() {
        return formularService.getAllFormulari();
    }

    @GetMapping("/formular/{id}")
    public Formular getFormularById(@PathVariable Integer id) {
        return formularService.getFormularById(id).orElse(null);
    }

    @PutMapping("/formular/{id}")
    public ResponseEntity<Formular> updateFormular(@PathVariable Integer id, @RequestBody Formular updatedFormular) {
        Formular result = formularService.updateFormular(id, updatedFormular);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/formular/{id}")
    public void deleteFormular(@PathVariable Integer id) {
        formularService.deleteFormular(id);
    }

}
