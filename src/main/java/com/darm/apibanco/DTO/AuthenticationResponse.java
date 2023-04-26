package com.darm.apibanco.DTO;

public record AuthenticationResponse(String token, PersonResponse person) {
}
