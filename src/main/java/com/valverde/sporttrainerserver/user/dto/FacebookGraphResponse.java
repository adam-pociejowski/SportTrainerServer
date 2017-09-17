package com.valverde.sporttrainerserver.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacebookGraphResponse {
    private String name;
    private String email;
    private String id;
}