package com.valverde.sporttrainerserver.service.parser;

import com.valverde.sporttrainerserver.enums.ActivityType;
import com.valverde.sporttrainerserver.web.dto.ActivityDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ActivityParser {
    ActivityDTO parse(MultipartFile file, ActivityType type) throws Exception;
}
