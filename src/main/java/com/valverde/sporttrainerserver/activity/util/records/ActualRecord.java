package com.valverde.sporttrainerserver.activity.util.records;

import com.valverde.sporttrainerserver.activity.enums.RecordType;
import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;
import lombok.Data;

@Data
class ActualRecord {
    private RecordType type;
    private TrackPointDTO startPoint;
    private TrackPointDTO endPoint;
}