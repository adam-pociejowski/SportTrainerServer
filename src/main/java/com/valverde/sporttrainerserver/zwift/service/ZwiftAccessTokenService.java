package com.valverde.sporttrainerserver.zwift.service;

import com.valverde.sporttrainerserver.zwift.dto.ZwiftTokenDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ZwiftAccessTokenService extends ZwiftApiService {

    ZwiftTokenDTO getToken() throws Exception {
        final MultiValueMap<String, String> formData = prepareTokenRequestFormData();
        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, prepareTokenHeaders());
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ZWIFT_TOKEN_API_URL);
        return postForEntity(builder, entity, ZwiftTokenDTO.class);
    }

    @NotNull
    private MultiValueMap<String, String> prepareTokenRequestFormData() {
        final MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "Zwift_Mobile_Link");
        formData.add("username", env.getProperty("ZWIFT_USERNAME"));
        formData.add("password", env.getProperty("ZWIFT_PASSWORD"));
        formData.add("grant_type", "password");
        return formData;
    }

    ZwiftAccessTokenService(final Environment env) {
        this.env = env;
    }

    private final Environment env;

    private static final String ZWIFT_TOKEN_API_URL = "https://secure.zwift.com/auth/realms/zwift/tokens/access/codes";
}