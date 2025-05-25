package com.fintech_middleware.Dashboard_Service.service;

import com.fintech_middleware.Dashboard_Service.domain.Account;
import com.fintech_middleware.Dashboard_Service.dto.request.AccountDto;
import com.fintech_middleware.Dashboard_Service.dto.request.CustomerDto;
import com.fintech_middleware.Dashboard_Service.dto.response.DashboardResponseDto;
import com.fintech_middleware.Dashboard_Service.repository.AccountRepository;
import com.fintech_middleware.Dashboard_Service.repository.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private  CustomerClient customerClient;
    @Autowired
    private  AccountRepository accountRepository;

    public DashboardResponseDto getDashboard(String email) {
        CustomerDto customer = customerClient.getCustomerByEmail(email);
        List<Account> accounts = accountRepository.findByCustomerEmail(email);

        List<AccountDto> accountDtos = accounts.stream()
                .map(a -> AccountDto.builder()
                        .accountNumber(a.getAccountNumber())
                        .accountType(a.getAccountType())
                        .balance(a.getBalance())
                        .build())
                .collect(Collectors.toList());

        return DashboardResponseDto.builder()
                .customer(customer)
                .accounts(accountDtos)
                .build();
    }
}
