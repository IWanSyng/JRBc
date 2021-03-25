package work.iwansyng.iwansyng.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import work.iwansyng.iwansyng.model.quiz.QuizItem;

import javax.persistence.AttributeConverter;
import java.util.List;

public class QuizItemListAttributeConverter implements AttributeConverter<List<QuizItem>, String> {

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<QuizItem> entityValue) {
        if (entityValue == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entityValue);
    }

    @SneakyThrows
    @Override
    public List<QuizItem> convertToEntityAttribute(String databaseValue) {
        if (databaseValue == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(databaseValue, new TypeReference<>() {});
    }
}
