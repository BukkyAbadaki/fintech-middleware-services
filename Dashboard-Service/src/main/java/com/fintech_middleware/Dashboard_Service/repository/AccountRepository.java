package com.fintech_middleware.Dashboard_Service.repository;

import com.fintech_middleware.Dashboard_Service.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerEmail(String email);

}
