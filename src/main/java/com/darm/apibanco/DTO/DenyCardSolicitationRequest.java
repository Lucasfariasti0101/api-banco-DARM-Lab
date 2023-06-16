package com.darm.apibanco.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DenyCardSolicitationRequest(@JsonProperty("message")
                                          @NotBlank
                                          @NotEmpty
                                          @Size(min = 30, max = 800)
                                          String message) {
}
