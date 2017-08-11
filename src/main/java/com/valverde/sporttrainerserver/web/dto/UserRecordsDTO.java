package com.valverde.sporttrainerserver.web.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class UserRecordsDTO {

    private Date intervalBegin;

    private Date intervalEnd;

    private List<ActivityRecordDTO> records;
}
