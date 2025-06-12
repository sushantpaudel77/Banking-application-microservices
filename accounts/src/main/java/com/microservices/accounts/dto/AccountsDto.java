package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of Bank",
            example = "7890902377"
    )
    @NotBlank(message = "AccountNumber cannot be a blank")
    @Pattern(regexp = "^\\d{10}$", message = "Account number must be exactly 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Bank",
            example = "Savings"
    )
    @NotBlank(message = "Account Type cannot be a blank")
    private String accountType;

    @Schema(
            description = "Bank branch address",
            example = "Kathmandu"
    )
    @NotBlank(message = "Branch address cannot be blank")
    private String branchAddress;

}
