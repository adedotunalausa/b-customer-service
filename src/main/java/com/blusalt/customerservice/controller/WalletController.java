package com.blusalt.customerservice.controller;

import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import com.blusalt.customerservice.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/wallet")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WalletController extends Controller{

    private final WalletService walletService;

    @PostMapping("/fund")
    public BasicResponse fundWallet(@Valid @RequestBody FundWalletRequest fundWalletRequest) {
        return walletService.fundWallet(fundWalletRequest);
    }

}
