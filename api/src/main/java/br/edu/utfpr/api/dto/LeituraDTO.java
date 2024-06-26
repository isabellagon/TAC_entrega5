package br.edu.utfpr.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LeituraDTO(
    @NotNull String valor,
    @NotNull @Min(1) Long sensorid) {
}
