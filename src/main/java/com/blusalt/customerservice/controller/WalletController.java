package com.blusalt.customerservice.controller;

import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import com.blusalt.customerservice.dto.response.FundWalletResponse;
import com.blusalt.customerservice.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/wallet")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WalletController extends Controller {

    private final WalletService walletService;

    @PostMapping("/fund")
    public BasicResponse fundWallet(@Valid @RequestBody FundWalletRequest fundWalletRequest) {
        return walletService.fundWallet(fundWalletRequest);
    }

    @PostMapping("/fund/complete/webhook")
    public BasicResponse completeWalletFunding(@Valid @RequestBody FundWalletResponse fundWalletResponse, @RequestHeader Map<String, String> requestHeaders) {
        return walletService.updateCustomerWallet(fundWalletResponse, requestHeaders);
    }

}
