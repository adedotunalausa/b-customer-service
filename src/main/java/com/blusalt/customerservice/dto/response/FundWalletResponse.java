package com.blusalt.customerservice.dto.response;

import com.blusalt.customerservice.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundWalletResponse {

    private Status status;
    private FundWalletResponseData data;

}
