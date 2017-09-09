package com.valverde.sporttrainerserver.activity.parser.enums;

import lombok.Getter;

public enum ActivityParserType {
    TCX_PARSER("tcx"),
    GPX_PARSER("gpx");

    ActivityParserType(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Getter
    private String fileExtension;
}