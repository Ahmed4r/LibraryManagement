package com.example.LibraryManagement.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorRequest {

    @NotBlank(message = "Author name is required")
    private String name;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    public AuthorRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}