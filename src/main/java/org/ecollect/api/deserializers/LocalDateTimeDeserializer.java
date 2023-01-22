package org.ecollect.api.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeParseException;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    protected LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }


    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        String strDate = jp.readValueAs(String.class);

        try { // if date is in ISO format
            Instant instant = Instant.parse(strDate);
            return  LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        } catch (DateTimeParseException e) {
            try { // if date is in "yyyy-mm-dd" format
                LocalDate date = LocalDate.parse(strDate);
                LocalTime time = LocalTime.of(0,0,0);
                return LocalDateTime.of(date, time);
            } catch (Exception unknownErr){
                throw unknownErr;
            }
        }
    }
}