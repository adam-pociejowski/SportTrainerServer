package com.valverde.sporttrainerserver.activity.parser;

import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ActivityParser {
    ActivityDTO parse(final MultipartFile file, final ActivityType type) throws Exception;
}
