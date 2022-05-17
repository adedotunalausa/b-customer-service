package com.blusalt.customerservice.init;

import com.blusalt.customerservice.dto.request.RoleDTO;
import com.blusalt.customerservice.enums.RoleType;
import com.blusalt.customerservice.exception.ResourceNotFoundException;
import com.blusalt.customerservice.model.Customer;
import com.blusalt.customerservice.model.Role;
import com.blusalt.customerservice.model.Wallet;
import com.blusalt.customerservice.repository.CustomerRepository;
import com.blusalt.customerservice.repository.RoleRepository;
import com.blusalt.customerservice.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public void run(ApplicationArguments args) {
        seedRoles();
        seedCustomer();
    }

    private void seedRoles() {
        Role role;

        if (roleRepository.findByName(RoleType.ADMIN).isEmpty()) {
            role = modelMapper.map(new RoleDTO(RoleType.ADMIN), Role.class);
            roleRepository.save(role);
        }

        if (roleRepository.findByName(RoleType.USER).isEmpty()) {
            role = modelMapper.map(new RoleDTO(RoleType.USER), Role.class);
            roleRepository.save(role);
        }
    }

    private void seedCustomer() {
        String username = "akanke";
        if (!customerRepository.existsByUsername(username)) {
            Customer customer = new Customer(
                    UUID.randomUUID().toString(),
                    username,
                    "Oluwadamilola",
                    "Alausa",
                    "female",
                    "akanke@gmail.com",
                    passwordEncoder.encode("123456")
            );

            Set<Role> roles = new HashSet<>();
            Role customerRole = roleRepository.findByName(RoleType.USER)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role not found."));
            roles.add(customerRole);
            customer.setRoles(roles);

            Wallet wallet = new Wallet(
                    UUID.randomUUID().toString(),
                    "1267.0",
                    customer.getCustomerId()
            );
            Wallet newWallet = walletRepository.save(wallet);
            customer.setWallet(newWallet);

            customerRepository.save(customer);
        }
    }
}
