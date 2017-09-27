package com.valverde.sporttrainerserver.zwift.service;

import com.valverde.sporttrainerserver.base.util.JsonUtils;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftTokenDTO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class ZwiftApiService {

    HttpHeaders prepareTokenHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    HttpHeaders prepareProtobufHeader(final ZwiftTokenDTO accessToken) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/x-protobuf-lite");
        headers.add("Authorization", "Bearer "+accessToken.getAccessToken());
        return headers;
    }

    <T> T postForEntity(final UriComponentsBuilder builder,
                       final HttpEntity entity,
                       final Class<T> clazz) throws Exception {
        final ResponseEntity<String> response = exchange(builder, entity, HttpMethod.POST);
        return JsonUtils.convertToObject(response.getBody(), clazz);
    }

    byte[] getForByteArray(final UriComponentsBuilder builder,
                           final HttpEntity entity) throws Exception {
        final ResponseEntity<byte[]> response = new RestTemplate().exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                byte[].class);
        return response.getBody();
    }

    private ResponseEntity<String> exchange(final UriComponentsBuilder builder,
                                            final HttpEntity entity,
                                            final HttpMethod httpMethod) {
        return new RestTemplate().exchange(
                builder.build().encode().toUri(),
                httpMethod,
                entity,
                String.class);
    }
}