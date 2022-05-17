package com.blusalt.customerservice.service.implementation;

import com.blusalt.customerservice.client.BillingFeignClient;
import com.blusalt.customerservice.dto.request.FundWalletRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import com.blusalt.customerservice.dto.response.FundWalletResponse;
import com.blusalt.customerservice.enums.Status;
import com.blusalt.customerservice.exception.ResourceNotFoundException;
import com.blusalt.customerservice.model.Customer;
import com.blusalt.customerservice.model.Wallet;
import com.blusalt.customerservice.repository.CustomerRepository;
import com.blusalt.customerservice.repository.WalletRepository;
import com.blusalt.customerservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final BillingFeignClient billingFeignClient;
    private final CustomerRepository customerRepository;
    private final WalletRepository walletRepository;

    @Value("${blusalt.customer.secret.key}")
    private String secretKey;

    @Override
    public BasicResponse fundWallet(FundWalletRequest fundWalletRequest) {
        try {
            Customer customer = getCustomer(fundWalletRequest.getCustomerId());
            FundWalletRequest updatedFundWalletRequest = updateFundWalletRequest(fundWalletRequest, customer);
            return billingFeignClient.fundWallet(updatedFundWalletRequest);
        } catch (Exception exception) {
            log.error("There was an error while funding wallet: {}", exception.getMessage());
            return new BasicResponse(Status.BAD_REQUEST, exception.getMessage());
        }
    }

    @Override
    public BasicResponse updateCustomerWallet(FundWalletResponse fundWalletResponse, Map<String, String> requestHeaders) {
        try {
            String secretKeyFromHeader = requestHeaders.get("secret_key");
            if (Objects.isNull(secretKeyFromHeader) || !secretKey.equals(secretKeyFromHeader)) {
                throw new ValidationException("Secret key is not present or not correct!");
            }
            Customer customer = getCustomer(fundWalletResponse.getCustomerId());
            updateCustomerWallet(customer.getCustomerId(), fundWalletResponse.getNewWalletBalance());
            return new BasicResponse(Status.SUCCESS);
        } catch (Exception exception) {
            log.error("There was an error while updating customer wallet: {}", exception.getMessage());
            return new BasicResponse(Status.FORBIDDEN);
        }
    }

    private Customer getCustomer(String customerId) {
        return customerRepository.findByCustomerId(customerId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Customer not found!");
        });
    }

    private Wallet getWallet(String customerId) {
        return walletRepository.findByCustomerId(customerId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Wallet not found!");
        });
    }

    private void updateCustomerWallet(String customerId, String newWalletBalance) {
        Wallet wallet = getWallet(customerId);
        wallet.setBalance(newWalletBalance);
        walletRepository.save(wallet);
    }

    private FundWalletRequest updateFundWalletRequest(FundWalletRequest fundWalletRequest, Customer customer) {
        Wallet wallet = customer.getWallet();
        fundWalletRequest.setWalletId(wallet.getWalletId());
        fundWalletRequest.setCurrentWalletBalance(wallet.getBalance());
        return fundWalletRequest;
    }

}
