package com.valverde.sporttrainerserver.zwift.rest;

import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;
import com.valverde.sporttrainerserver.zwift.service.ZwiftRiderStatusService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
public class ZwiftRestController {

    @GetMapping("/zwift/getRiderStatus")
    public ResponseEntity<RiderStateDTO> getRiderStatus() {
        try {
            final RiderStateDTO riderState = zwiftRiderStatusService.getRiderStatus();
            return new ResponseEntity<>(riderState, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception while getting rider status", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ZwiftRestController(final ZwiftRiderStatusService zwiftRiderStatusService) {
        this.zwiftRiderStatusService = zwiftRiderStatusService;
    }

    private final ZwiftRiderStatusService zwiftRiderStatusService;
}