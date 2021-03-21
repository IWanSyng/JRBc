package work.iwansyng.iwansyng.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import work.iwansyng.iwansyng.models.quiz.Quiz;
import work.iwansyng.iwansyng.models.quiz.QuizItem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class QuizListAttributeConverter implements AttributeConverter<List<Quiz>, String> {

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<Quiz> entityValue) {
        if (entityValue == null || entityValue.isEmpty())
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entityValue);
    }

    public String convertPretty(List<Quiz> entityValue) throws JsonProcessingException {
        if (entityValue == null || entityValue.isEmpty())
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entityValue);
    }

    @SneakyThrows
    @Override
    public List<Quiz> convertToEntityAttribute(String databaseValue) {
        if (databaseValue == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(databaseValue, new TypeReference<>() {});
    }
}