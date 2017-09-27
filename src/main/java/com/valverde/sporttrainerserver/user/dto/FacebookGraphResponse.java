package com.valverde.sporttrainerserver.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacebookGraphResponse {

    private String name;

    private String email;

    private String id;
}