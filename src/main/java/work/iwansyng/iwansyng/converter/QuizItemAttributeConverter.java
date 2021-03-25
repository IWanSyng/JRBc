package work.iwansyng.iwansyng.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import work.iwansyng.iwansyng.model.quiz.QuizItem;

import javax.persistence.AttributeConverter;

public class QuizItemAttributeConverter implements AttributeConverter<QuizItem, String> {

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(QuizItem entityValue) {
        if (entityValue == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entityValue);
    }

    @SneakyThrows
    @Override
    public QuizItem convertToEntityAttribute(String databaseValue) {
        if (databaseValue == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(databaseValue, new TypeReference<>() {});
    }
}
