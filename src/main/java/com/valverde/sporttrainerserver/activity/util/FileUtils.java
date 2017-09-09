package com.valverde.sporttrainerserver.activity.util;

import com.valverde.sporttrainerserver.base.util.AppUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static Element getXmlRoot(MultipartFile multipartFile) {
        File file = null;
        try {
            file = convertToFile(multipartFile);
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(file);
            return document.getRootElement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            deleteFile(file);
        }
    }

    public static File convertToFile(MultipartFile multipartFile) throws IOException {
        final File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }

    public static void deleteFile(File file) {
        if (AppUtils.isNotNull(file))
            file.delete();
    }
}
