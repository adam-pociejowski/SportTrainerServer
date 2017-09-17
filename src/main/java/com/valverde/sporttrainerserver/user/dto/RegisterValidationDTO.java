package com.valverde.sporttrainerserver.user.dto;

import com.valverde.sporttrainerserver.user.enums.ValidationErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterValidationDTO {

    private ValidationErrorType type;

    private String message;
}
