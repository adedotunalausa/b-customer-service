package com.blusalt.customerservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("male"),
    FEMALE("male");

    private final String alias;

    private static final Map<String, Gender> genderTypeMap = new HashMap<>();

    static {
        for (Gender genderType : Gender.values()) {
            genderTypeMap.put(genderType.alias, genderType);
        }
    }

    public static Gender getByAlias(String alias) {
        return genderTypeMap.get(alias);
    }

    @Override
    public String toString() {
        return this.alias;
    }

}
