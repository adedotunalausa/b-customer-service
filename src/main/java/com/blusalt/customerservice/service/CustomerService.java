package com.blusalt.customerservice.service;

import com.blusalt.customerservice.dto.request.LoginRequest;
import com.blusalt.customerservice.dto.request.SignupRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;

public interface CustomerService {

    BasicResponse registerCustomer(SignupRequest customerDetails);
    BasicResponse authenticateCustomer(LoginRequest customerDetails);
}
