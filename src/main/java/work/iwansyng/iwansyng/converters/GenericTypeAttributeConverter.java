package work.iwansyng.iwansyng.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import work.iwansyng.iwansyng.models.quiz.AnswerType;
import work.iwansyng.iwansyng.models.quiz.QuizItem;

import javax.persistence.AttributeConverter;
import java.util.List;

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
