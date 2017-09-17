package com.valverde.sporttrainerserver.user.service;

import com.valverde.sporttrainerserver.user.dto.FacebookDataDTO;
import com.valverde.sporttrainerserver.user.dto.FacebookGraphResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FacebookService {

    public boolean checkIfTokenIsValid(final FacebookDataDTO facebookData) {
        final HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GRAPH_API_URL)
                .queryParam("fields", "name,email")
                .queryParam("access_token", facebookData.getToken());

        final ResponseEntity<FacebookGraphResponse> response = new RestTemplate().exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                FacebookGraphResponse.class);
        final FacebookGraphResponse graphResponse = response.getBody();
        return graphResponse.getEmail().equals(facebookData.getEmail());
    }

    private static final String GRAPH_API_URL = "https://graph.facebook.com/me";
}