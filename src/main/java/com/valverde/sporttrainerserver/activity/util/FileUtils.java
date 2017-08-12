package com.valverde.sporttrainerserver.activity.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static Element getXmlRoot(MultipartFile multipartFile) {
        try {
            File file = convertToFile(multipartFile);
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(file);
            return document.getRootElement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }
}
