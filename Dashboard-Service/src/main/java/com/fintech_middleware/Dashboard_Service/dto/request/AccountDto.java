package com.fintech_middleware.Dashboard_Service.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class AccountDto {
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String status;
}
