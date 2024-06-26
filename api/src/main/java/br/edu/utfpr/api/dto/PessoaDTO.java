package br.edu.utfpr.api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record PessoaDTO(
    @NotBlank @Length(min = 2) String nome, 
    @NotBlank @Length(min = 2) String email, 
    @NotBlank @Length(min = 2) String senha) {

        public String getSenha() {
            return senha;
        }
}
