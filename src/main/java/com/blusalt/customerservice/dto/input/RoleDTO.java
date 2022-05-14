package com.blusalt.customerservice.dto.input;

import com.blusalt.customerservice.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {

    private RoleType name;
}
