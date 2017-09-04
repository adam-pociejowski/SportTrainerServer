package com.valverde.sporttrainerserver.statistics.util;

import com.valverde.sporttrainerserver.statistics.enums.RecordType;
import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;
import lombok.Data;

@Data
class ActualRecord {
    private RecordType type;
    private TrackPointDTO startPoint;
    private TrackPointDTO endPoint;
}