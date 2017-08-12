package com.valverde.sporttrainerserver.results.util;

import com.valverde.sporttrainerserver.results.enums.RecordType;
import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;
import lombok.Data;

@Data
class ActualRecord {
    private RecordType type;
    private TrackPointDTO startPoint;
    private TrackPointDTO endPoint;
}