package com.valverde.sporttrainerserver.base.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static <T> T convertToObject(final String json,
                                        final Class<T> clazz) throws Exception {
        return new ObjectMapper().readValue(json, clazz);
    }
}