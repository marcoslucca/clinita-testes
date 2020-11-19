package br.com.fundatec.clinicaveterinaria.controller.request;

import javax.validation.constraints.NotBlank;

public class TutorRequest {

    @NotBlank(message = "Name is mandatory")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
