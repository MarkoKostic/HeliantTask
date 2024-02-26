package com.example.demo.dto;

import lombok.Data;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Data
public class SignUpRequest {

    private String korisnickoIme;
    private String lozinka;
    private String email;
}
