package com.valverde.sporttrainerserver.zwift.service;

import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftTokenDTO;
import com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ZwiftRiderStatusService extends ZwiftApiService {

    public RiderStateDTO getRiderStatus() throws Exception {
        final ZwiftTokenDTO accessToken = getAccessToken();
        final UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(ZWIFT_TOKEN_API)
                .path("/relay/worlds/1/players/9618");
        final HttpEntity entity = new HttpEntity<>(prepareProtobufHeader(accessToken));
        final byte[] bytes = getForByteArray(builder, entity);
        final ZwiftMessages.PlayerState playerState = ZwiftMessages.PlayerState.parseFrom(bytes);
        return new RiderStateDTO(playerState);
    }

    private ZwiftTokenDTO getAccessToken() throws Exception {
        final ZwiftTokenDTO accessToken = zwiftAccessTokenService.getToken();
        return accessToken;
    }

    public ZwiftRiderStatusService(final ZwiftAccessTokenService zwiftAccessTokenService) {
        this.zwiftAccessTokenService = zwiftAccessTokenService;
    }

    private final ZwiftAccessTokenService zwiftAccessTokenService;

    private static final String ZWIFT_TOKEN_API = "https://us-or-rly101.zwift.com";
}
