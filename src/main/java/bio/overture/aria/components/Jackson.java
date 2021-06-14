package bio.overture.aria.components;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.val;

public class Jackson {
  private static final String SINGLE_QUOTE = "'";
  private static final String DOUBLE_QUOTE = "\"";

  protected static final ObjectMapper mapper = mapper();

  public static ObjectMapper mapper() {
    val mapper = new ObjectMapper();

    /* Register Moduless */
    val isoDateFormatter = DateTimeFormatter.ISO_DATE_TIME;
    val dateTimeDeserializer = new LocalDateTimeDeserializer(isoDateFormatter);
    val dateTimeSerializer = new LocalDateTimeSerializer(isoDateFormatter);

    val javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
    javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);

    mapper.registerModule(javaTimeModule);

    mapper.disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
    mapper.disable(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

    return mapper;
  }

  public static <T> T convertValue(Object fromValue, Class<T> toValue) {
    return mapper().convertValue(fromValue, toValue);
  }
}
