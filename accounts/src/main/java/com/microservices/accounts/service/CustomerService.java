package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomersDetailsDto;

public interface CustomerService {
    CustomersDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
