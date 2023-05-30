package com.darm.apibanco.confg;

import java.time.Instant;

public record ExceptionDetails(String message,
                               int status,
                               Instant timestamp,
                               String path) {
}