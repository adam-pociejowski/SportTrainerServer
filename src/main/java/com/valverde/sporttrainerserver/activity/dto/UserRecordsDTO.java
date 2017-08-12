package com.valverde.sporttrainerserver.activity.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class UserRecordsDTO {

    private Date intervalBegin;

    private Date intervalEnd;

    private List<ActivityRecordDTO> records;
}
