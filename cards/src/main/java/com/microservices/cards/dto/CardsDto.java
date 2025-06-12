package com.microservices.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(name = "Cards", description = "Schema to hold Card information")
@Data
public class CardsDto {

    @NotBlank(message = "Mobile Number cannot be null, empty or just whitespace")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number must be exactly 10 digits")
    @Schema(description = "Mobile Number of Customer", example = "4354437687")
    private String mobileNumber;

    @NotBlank(message = "Card Number cannot be null, empty or just whitespace")
    @Pattern(regexp = "^\\d{12}$", message = "Card Number must be exactly 12 digits")
    @Schema(description = "Card Number of the customer", example = "100646930341")
    private String cardNumber;

    @NotBlank(message = "Card Type cannot be null, empty or just whitespace")
    @Schema(description = "Type of the card", example = "Credit Card")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(description = "Total amount limit available against a card", example = "100000")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(description = "Total amount used by a Customer", example = "1000")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(description = "Total available amount against a card", example = "90000")
    private int availableAmount;
}

