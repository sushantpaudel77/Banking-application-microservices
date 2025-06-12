package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Represents a standardized response for successful API operations"
)
@Data
@AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "Status code indicating the result of the operation",
            example = "201"
    )
    private String statusCode;

    @Schema(
            description = "Human-readable message describing the status",
            example = "Account successfully created"
    )
    private String statusMsg;
}
