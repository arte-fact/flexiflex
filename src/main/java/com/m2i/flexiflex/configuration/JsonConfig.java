package com.m2i.flexiflex.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Configuration
public class JsonConfig {

    public static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String TIME_ZONE_UTC = "UTC";

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        // to remove null from response
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat formatterUTC = new SimpleDateFormat(FORMAT_DATE);
        formatterUTC.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_UTC));
        objectMapper.setDateFormat(formatterUTC);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE);
        javaTimeModule.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
        javaTimeModule.addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
        javaTimeModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        objectMapper.registerModule(javaTimeModule);
        jsonConverter.setObjectMapper(objectMapper);

        return jsonConverter;
    }
}
