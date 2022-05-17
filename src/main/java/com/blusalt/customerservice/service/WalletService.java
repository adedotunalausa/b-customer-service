package com.blusalt.customerservice.service;

import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import com.blusalt.customerservice.dto.response.FundWalletResponse;

import java.util.Map;

public interface WalletService {

    BasicResponse fundWallet(FundWalletRequest fundWalletRequest);
    BasicResponse updateCustomerWallet(FundWalletResponse fundWalletResponse, Map<String, String> requestHeaders);

}
