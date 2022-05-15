package com.blusalt.customerservice.service;

import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;

public interface WalletService {

    BasicResponse fundWallet(FundWalletRequest fundWalletRequest);

}
