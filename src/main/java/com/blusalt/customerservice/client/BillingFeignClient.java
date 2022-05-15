package com.blusalt.customerservice.client;

import com.blusalt.customerservice.config.Routes;
import com.blusalt.customerservice.config.billing.BillingFeignClientConfig;
import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "billing-service", url = "${blusalt.billing.base.url}", configuration = BillingFeignClientConfig.class)
public interface BillingFeignClient {

    @PostMapping(Routes.Billing.Wallet.FUND_WALLET)
    BasicResponse fundWallet(FundWalletRequest fundWalletRequest);

}
