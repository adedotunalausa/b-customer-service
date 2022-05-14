package com.blusalt.customerservice.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private String email;
    private List<String> roles;

    public JwtResponseDTO(String accessToken, String email, List<String> roles) {
        this.token = accessToken;
        this.email = email;
        this.roles = roles;
    }

}
