package com.fintech_middleware.Dashboard_Service.dto.response;

import com.fintech_middleware.Dashboard_Service.dto.request.AccountDto;
import com.fintech_middleware.Dashboard_Service.dto.request.CustomerDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DashboardResponseDto {
    private CustomerDto customer;

    private List<AccountDto> accounts;

}
