package com.microservices.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotBlank(message = "AccountNumber cannot be a blank")
    @Pattern(regexp = "^\\d{10}$", message = "Account number must be exactly 10 digits")
    private Long accountNumber;

    @NotBlank(message = "Account Type cannot be a blank")
    private String accountType;

    @NotBlank(message = "Branch address cannot be blank")
    private String branchAddress;

}
