package com.example.demo.controller;

import com.example.demo.dto.FormularDto;
import com.example.demo.entity.Formular;
import com.example.demo.service.FormularService;
import com.example.demo.validation.GroupWithOptionalFields;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public FormularDto createFormular(@RequestBody @Valid FormularDto formularDto) {
        FormularDto savedFormular = formularService.saveFormular(formularDto);
        return new ResponseEntity<>(savedFormular, HttpStatus.CREATED).getBody();
    }

    // createFormulars

    // (FormularsDto lista formulara)

    @GetMapping("/formulari")
    public ResponseEntity<Page<Formular>> getAllFormulari(@PageableDefault(size = 10) Pageable pageable) {
        Page<Formular> formulars = formularService.getAllFormulari(pageable);
        return ResponseEntity.ok(formulars);
    }

    @GetMapping("/formular/{id}")
    public Formular getFormularById(@PathVariable Integer id) {
        return formularService.getFormularById(id).orElse(null);
    }

    @PutMapping("/formular/{id}")
    public ResponseEntity<FormularDto> updateFormular(@PathVariable Integer id, @RequestBody FormularDto formularDto) {
        FormularDto updatedFormularDto = formularService.updateFormular(id, formularDto);
        return ResponseEntity.ok(updatedFormularDto);
    }

    @DeleteMapping("/formular/{id}")
    public ResponseEntity<String> deleteFormular(@PathVariable Integer id) {
        formularService.deleteFormular(id);
        return ResponseEntity.ok("Formular successfully deleted");
    }

}
