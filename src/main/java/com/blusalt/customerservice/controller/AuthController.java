package com.blusalt.customerservice.controller;

import com.blusalt.customerservice.dto.input.LoginDTO;
import com.blusalt.customerservice.dto.input.SignupDTO;
import com.blusalt.customerservice.dto.output.BasicResponseDTO;
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
    public BasicResponseDTO login(@Valid @RequestBody LoginDTO userDetails) {
        return responseWithUpdatedHttpStatus(customerService.authenticateCustomer(userDetails));
    }

    @PostMapping("/signup")
    public BasicResponseDTO register(@Valid @RequestBody SignupDTO userDetails) {
        return responseWithUpdatedHttpStatus(customerService.registerCustomer(userDetails));
    }

}
