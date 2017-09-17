package com.valverde.sporttrainerserver.user.dto;

import lombok.Data;

@Data
public class FacebookDataDTO {
    private String name;

    private String email;

    private String token;

    private String uid;
}
