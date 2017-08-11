package com.valverde.sporttrainerserver.util.records;

import com.valverde.sporttrainerserver.enums.RecordType;
import com.valverde.sporttrainerserver.web.dto.TrackPointDTO;
import lombok.Data;

@Data
class ActualRecord {
    private RecordType type;
    private TrackPointDTO startPoint;
    private TrackPointDTO endPoint;
}