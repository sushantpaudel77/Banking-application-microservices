package com.microservices.loans.service;

import com.microservices.loans.dto.LoansDto;

public interface LoanService {

    void createLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String mobileNumber);
}
