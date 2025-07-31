package com.raksa.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.raksa.app.exception.exceptionHandle.IllegalArgumentException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class DataConverterUtils<T> {


    @SuppressWarnings("unchecked")
    public static <T> T convertXmlToDto(String xmlContent, Class<T> dtoClass) {
        try {
            JAXBContext context = JAXBContext.newInstance(dtoClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlContent);
            return (T) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error converting XML to DTO: " + e.getMessage(), e);
        }
    }

    public static <T> T convertXmlContentToDto(String xmlContent, Class<T> dtoClass) {
        if (xmlContent == null || xmlContent.trim().isEmpty()) {
            throw new IllegalArgumentException("XML content cannot be null or empty");
        }
        try {
            XmlMapper xmlMapper = ApplicationContextCustomUtils.getBeanName(XmlMapper.class);
            return xmlMapper.readValue(xmlContent, dtoClass);
        } catch (Exception e) {
            throw new RuntimeException("Error converting XML content to DTO: " + e.getMessage(), e);
        }
    }

    public String convertDtoToJson(T dto) {
        try {
            ObjectMapper objectMapper = ApplicationContextCustomUtils.getBeanName(ObjectMapper.class);
            return objectMapper.writeValueAsString(dto);
        } catch (Exception e) {
            throw new RuntimeException("Error converting DTO to JSON: " + e.getMessage(), e);
        }
    }

}