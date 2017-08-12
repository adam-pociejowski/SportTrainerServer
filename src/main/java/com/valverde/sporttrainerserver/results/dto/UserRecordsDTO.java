package com.valverde.sporttrainerserver.results.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserRecordsDTO {

    private Date intervalBegin;

    private Date intervalEnd;

    private List<ActivityRecordDTO> records;
}
