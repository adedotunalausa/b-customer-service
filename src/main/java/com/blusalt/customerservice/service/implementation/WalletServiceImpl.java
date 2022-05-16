package com.blusalt.customerservice.service.implementation;

import com.blusalt.customerservice.client.BillingFeignClient;
import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import com.blusalt.customerservice.exception.ResourceNotFoundException;
import com.blusalt.customerservice.model.Customer;
import com.blusalt.customerservice.model.Wallet;
import com.blusalt.customerservice.repository.CustomerRepository;
import com.blusalt.customerservice.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final BillingFeignClient billingFeignClient;
    private final CustomerRepository customerRepository;

    @Override
    public BasicResponse fundWallet(FundWalletRequest fundWalletRequest) {
        Customer customer = getCustomer(fundWalletRequest.getCustomerId());
        FundWalletRequest updatedFundWalletRequest = updateFundWalletRequest(fundWalletRequest, customer);
        return billingFeignClient.fundWallet(updatedFundWalletRequest);
    }

    private Customer getCustomer(String customerId) {
        return customerRepository.findByCustomerId(customerId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Customer not found!");
        });
    }

    private FundWalletRequest updateFundWalletRequest(FundWalletRequest fundWalletRequest, Customer customer) {
        Wallet wallet = customer.getWallet();
        fundWalletRequest.setWalletId(wallet.getWalletId());
        fundWalletRequest.setCurrentWalletBalance(wallet.getBalance());
        return fundWalletRequest;
    }
}
