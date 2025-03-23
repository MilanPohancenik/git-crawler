package com.pohancenik.gitcrawler.infrastructure.exception;

import java.time.Instant;

public record ErrorResponse(Instant timestamp, int statusCode, String status, String message) {
}
