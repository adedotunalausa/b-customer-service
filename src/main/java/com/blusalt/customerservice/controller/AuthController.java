package com.blusalt.customerservice.controller;

import com.blusalt.customerservice.dto.request.LoginRequest;
import com.blusalt.customerservice.dto.request.SignupRequest;
import com.blusalt.customerservice.dto.response.BasicResponse;
import com.blusalt.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController extends Controller {

    private final CustomerService customerService;

    @PostMapping("/login")
    public BasicResponse login(@Valid @RequestBody LoginRequest userDetails) {
        return responseWithUpdatedHttpStatus(customerService.authenticateCustomer(userDetails));
    }

    @PostMapping("/signup")
    public BasicResponse register(@Valid @RequestBody SignupRequest userDetails) {
        return responseWithUpdatedHttpStatus(customerService.registerCustomer(userDetails));
    }

}
