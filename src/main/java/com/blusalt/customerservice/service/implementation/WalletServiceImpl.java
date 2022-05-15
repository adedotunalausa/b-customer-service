package com.blusalt.customerservice.service.implementation;

import com.blusalt.customerservice.client.BillingFeignClient;
import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import com.blusalt.customerservice.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final BillingFeignClient billingFeignClient;

    @Override
    public BasicResponse fundWallet(FundWalletRequest fundWalletRequest) {
        return billingFeignClient.fundWallet(fundWalletRequest);
    }
}
