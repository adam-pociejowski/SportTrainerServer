package com.valverde.sporttrainerserver.user.rest;

import com.valverde.sporttrainerserver.base.security.JwtTokenUtil;
import com.valverde.sporttrainerserver.base.security.service.JwtAuthenticationResponse;
import com.valverde.sporttrainerserver.user.dto.FacebookDataDTO;
import com.valverde.sporttrainerserver.user.service.FacebookService;
import lombok.extern.apachecommons.CommonsLog;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
public class FacebookLoginRestService {

    @PostMapping("/login/facebook")
    public ResponseEntity loginByFacebook(@RequestBody FacebookDataDTO facebookData) {
        try {
            if (facebookService.checkIfTokenIsValid(facebookData)) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(facebookData.getEmail());
                final String token = jwtTokenUtil.generateToken(userDetails, getNormalDevice());
                return ResponseEntity.ok(new JwtAuthenticationResponse(token));
            }
            log.info("Facebook token is not valid "+facebookData.toString());
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Exception while logging by facebook "+facebookData.toString(), e);
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @NotNull
    private Device getNormalDevice() {
        return new Device() {
            @Override
            public boolean isNormal() {
                return true;
            }

            @Override
            public boolean isMobile() {
                return false;
            }

            @Override
            public boolean isTablet() {
                return false;
            }

            @Override
            public DevicePlatform getDevicePlatform() {
                return null;
            }
        };
    }

    public FacebookLoginRestService(final FacebookService facebookService,
                                    final JwtTokenUtil jwtTokenUtil,
                                    final UserDetailsService userDetailsService) {
        this.facebookService = facebookService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    private final FacebookService facebookService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;
}
