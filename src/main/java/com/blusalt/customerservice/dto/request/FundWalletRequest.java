package com.blusalt.customerservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundWalletRequest {

    private String customerId;

    private Double amount;

}
