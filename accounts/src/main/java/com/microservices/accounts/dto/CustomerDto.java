package com.microservices.accounts.dto;

import com.microservices.accounts.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
public class CustomerDto {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
}
