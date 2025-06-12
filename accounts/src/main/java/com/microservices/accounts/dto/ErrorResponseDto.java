package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to represent standardized error responses returned by the API when a request fails"
)
public class ErrorResponseDto {

    @Schema(
            description = "The URI path of the API endpoint that caused the error",
            example = "/api/fetch"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code indicating the type of error that occurred",
            example = "BAD_REQUEST"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "A human-readable message providing more details about the error",
            example = "Mobile number must be exactly 10 digits"
    )
    private String errorMessage;

    @Schema(
            description = "The timestamp at which the error occurred",
            example = "2025-06-12T15:30:00"
    )
    private LocalDateTime errorTime;
}

