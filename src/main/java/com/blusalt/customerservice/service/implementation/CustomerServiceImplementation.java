package com.blusalt.customerservice.service.implementation;

import com.blusalt.customerservice.dto.input.LoginDTO;
import com.blusalt.customerservice.dto.input.SignupDTO;
import com.blusalt.customerservice.dto.output.BasicResponseDTO;
import com.blusalt.customerservice.dto.output.JwtResponseDTO;
import com.blusalt.customerservice.enums.Gender;
import com.blusalt.customerservice.enums.RoleType;
import com.blusalt.customerservice.enums.Status;
import com.blusalt.customerservice.exception.ResourceNotFoundException;
import com.blusalt.customerservice.model.Customer;
import com.blusalt.customerservice.model.Role;
import com.blusalt.customerservice.model.Wallet;
import com.blusalt.customerservice.repository.CustomerRepository;
import com.blusalt.customerservice.repository.RoleRepository;
import com.blusalt.customerservice.repository.WalletRepository;
import com.blusalt.customerservice.security.service.CustomerDetailsImpl;
import com.blusalt.customerservice.service.CustomerService;
import com.blusalt.customerservice.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;

    private final WalletRepository walletRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public BasicResponseDTO registerCustomer(SignupDTO customerDetails) {

        if (emailExists(customerDetails.getEmail())) {
            return new BasicResponseDTO(Status.FAILED_VALIDATION, "Email is already in use");
        }

        if (usernameExists(customerDetails.getUsername())) {
            return new BasicResponseDTO(Status.FAILED_VALIDATION, "Username is already taken");
        }

        Customer newCustomer = createCustomerObjectFromSignupInfo(customerDetails);
        setRolesForNewCustomer(newCustomer);
        Wallet newWallet = createWalletForCustomer(newCustomer);
        newCustomer.setWallet(newWallet);
        customerRepository.save(newCustomer);

        return new BasicResponseDTO(Status.CREATED, newCustomer);

    }

    @Override
    public BasicResponseDTO authenticateCustomer(LoginDTO customerDetails) {

        if (!emailExists(customerDetails.getEmail())) {
            return new BasicResponseDTO(Status.NOT_FOUND, "Error: Customer not found! Make sure email is correct " +
                    "or signup if you don't have an account");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerDetails.getEmail(), customerDetails.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerDetailsImpl customerInfo = (CustomerDetailsImpl) authentication.getPrincipal();

        List<String> roles = customerInfo.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new BasicResponseDTO(Status.SUCCESS, new JwtResponseDTO(jwt, customerInfo.getUsername(), roles));

    }

    private Customer createCustomerObjectFromSignupInfo(SignupDTO customerDetails) {
        return new Customer(
                UUID.randomUUID().toString(),
                customerDetails.getUsername(),
                customerDetails.getFirstname(),
                customerDetails.getLastname(),
                Gender.getByAlias(customerDetails.getGender()),
                customerDetails.getEmail(),
                passwordEncoder.encode(customerDetails.getPassword())
        );
    }

    private boolean emailExists(String email) {
        return customerRepository.existsByEmail(email);
    }

    private boolean usernameExists(String username) {
        return customerRepository.existsByUsername(username);
    }

    private void setRolesForNewCustomer(Customer newUser) {
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleRepository.findByName(RoleType.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role not found."));
        roles.add(customerRole);
        newUser.setRoles(roles);
    }

    private Wallet createWalletForCustomer(Customer newCustomer) {
        Wallet wallet = new Wallet(
                UUID.randomUUID().toString(),
                0.0,
                newCustomer.getCustomerId()
        );
        return walletRepository.save(wallet);
    }

}
