package com.valverde.sporttrainerserver.register.dto;

import com.valverde.sporttrainerserver.register.enums.ValidationErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterValidationDTO {

    private ValidationErrorType type;

    private String message;
}
