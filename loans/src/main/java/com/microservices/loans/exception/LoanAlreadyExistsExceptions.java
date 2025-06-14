package com.microservices.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsExceptions extends RuntimeException {
    public LoanAlreadyExistsExceptions(String message) {
    }
}
