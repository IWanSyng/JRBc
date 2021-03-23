package work.iwansyng.iwansyng.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class GenericTypeAttributeConverter implements AttributeConverter<Object, String> {

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object entityValue) {
        if (entityValue == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entityValue);
    }

    @SneakyThrows
    @Override
    public Object convertToEntityAttribute(String databaseValue) {
        if (databaseValue == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(databaseValue, new TypeReference<>() {});
    }
}
