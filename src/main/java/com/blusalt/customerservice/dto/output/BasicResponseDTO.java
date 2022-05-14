package com.blusalt.customerservice.dto.output;

import com.blusalt.customerservice.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponseDTO extends StandardResponseDTO {

    @JsonProperty
    private Object responseData;

    public BasicResponseDTO(Status status) {
        super(status);
    }

    public BasicResponseDTO(Status status, Object responseData) {
        super(status);
        this.responseData = responseData;
    }

}
