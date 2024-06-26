package br.edu.utfpr.api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GatewayDTO(
    @NotBlank @Length(min=2) String nome,
    @NotBlank @Length(min=2) String descricao,
    @NotNull @Min(1) Long pessoaid
) {

}
