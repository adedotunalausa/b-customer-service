package com.blusalt.customerservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundWalletResponse {

    private String customerId;
    private String amount;
    private String walletId;
    private String currentWalletBalance;
    private String newWalletBalance;
    private String transactionStatus;

}
