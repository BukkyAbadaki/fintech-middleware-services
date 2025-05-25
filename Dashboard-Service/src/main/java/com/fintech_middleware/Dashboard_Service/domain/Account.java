package com.fintech_middleware.Dashboard_Service.domain;

import com.fintech_middleware.Dashboard_Service.dto.request.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private String accountType;  // e.g., Savings, Current

    private BigDecimal balance;

    private String status; // active, inactive

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
//    private CustomerDto customer;

}
