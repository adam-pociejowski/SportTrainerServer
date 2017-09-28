package com.valverde.sporttrainerserver.zwift.service;

import com.valverde.sporttrainerserver.base.util.AppUtils;
import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftTokenDTO;
import com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Date;

@Service
@CommonsLog
public class ZwiftRiderStatusService extends ZwiftApiService {

    RiderStateDTO getRiderStatus(final String riderId) throws Exception {
        getAccessToken();
        final UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(ZWIFT_TOKEN_API)
                .path("/relay/worlds/")
                .path(WORLD_ID)
                .path("/players/")
                .path(riderId);

        final HttpEntity entity = new HttpEntity<>(prepareProtobufHeader(accessToken));
        final byte[] bytes = getForByteArray(builder, entity);
        final ZwiftMessages.PlayerState playerState = ZwiftMessages.PlayerState.parseFrom(bytes);
        return new RiderStateDTO(playerState);
    }

    private void getAccessToken() throws Exception {
        if (AppUtils.isNull(accessToken) || zwiftAccessTokenService.isTokenExpired(accessToken)) {
            accessToken = zwiftAccessTokenService.getToken();
            accessToken.setTokenGenerated(new Date());
            log.info("Zwift access token generated "+accessToken.getAccessToken()+". " +
                    "Expires in: "+accessToken.getExpiresIn());
        }
    }

    public ZwiftRiderStatusService(final ZwiftAccessTokenService zwiftAccessTokenService) {
        this.zwiftAccessTokenService = zwiftAccessTokenService;
    }

    private final ZwiftAccessTokenService zwiftAccessTokenService;

    private ZwiftTokenDTO accessToken;

    private static final String ZWIFT_TOKEN_API = "https://us-or-rly101.zwift.com";

    private static final String WORLD_ID = "1";
}