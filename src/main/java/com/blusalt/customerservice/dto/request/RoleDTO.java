package com.blusalt.customerservice.dto.request;

import com.blusalt.customerservice.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleDTO {

    private RoleType name;
}
