package com.valverde.sporttrainerserver.activity.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;

@Service
public class JaxbService {

    @NotNull
    @SuppressWarnings("unchecked")
    public <T> T unMarshall(File file, Class<T> clazz) throws JAXBException, ClassCastException {
        final JAXBContext jc = JAXBContext.newInstance(clazz);
        final Unmarshaller unmarshaller = jc.createUnmarshaller();
        final JAXBElement<T> element = JAXBElement.class.cast(unmarshaller.unmarshal(file));
        return element.getValue();
    }
}