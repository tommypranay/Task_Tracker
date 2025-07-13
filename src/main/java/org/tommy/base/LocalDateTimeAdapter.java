package org.tommy.base;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    /**
     * A Gson adapter to serialize and deserialize {@link LocalDateTime}
     * using ISO-8601 format (e.g., "2025-07-11T19:00:00").
     *
     * This adapter ensures that {@code LocalDateTime} is properly
     * written to and read from JSON as a human-readable string.
     */


    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * Converts a JSON string to a {@link LocalDateTime} using ISO format.
     */
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(jsonElement.getAsString(),formatter);
    }

    /**
     * Converts a {@link LocalDateTime} to a JSON string using ISO format.
     */
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDateTime.format(formatter));
    }
}
