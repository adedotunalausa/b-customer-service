package com.blusalt.customerservice.service;

import com.blusalt.customerservice.dto.input.LoginDTO;
import com.blusalt.customerservice.dto.input.SignupDTO;
import com.blusalt.customerservice.dto.output.BasicResponseDTO;

public interface CustomerService {

    BasicResponseDTO registerUser(SignupDTO userDetails);
    BasicResponseDTO authenticateUser(LoginDTO userDetails);
}
