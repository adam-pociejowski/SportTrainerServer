package com.valverde.sporttrainerserver.activity.service.parser;

import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ActivityParser {
    ActivityDTO parse(MultipartFile file, ActivityType type) throws Exception;
}
