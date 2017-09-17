package com.valverde.sporttrainerserver.user.enums;

import lombok.Getter;

public enum Country {
    POLAND("Poland");

    @Getter
    private String name;

    Country(String name) {
        this.name = name;
    }
}
