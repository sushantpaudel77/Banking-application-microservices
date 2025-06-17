package com.microservices.accounts.controller;

import com.microservices.accounts.dto.CustomersDetailsDto;
import com.microservices.accounts.dto.ErrorResponseDto;
import com.microservices.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
        name = "REST APIs accounts for my-Banking application",
        description = "REST APIs in my-Banking to FETCH customer details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomersDetailsDto> fetchCustomerDetails(
            @RequestHeader("ezypay-correlation-id") String correlationId,
            @RequestParam
            @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be exactly 10 digits")
            String mobileNumber
    ) {
        log.debug("ezyPay-correlation-id found:{}", correlationId);
        CustomersDetailsDto customersDetailsDto = customerService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.ok(customersDetailsDto);
    }
}