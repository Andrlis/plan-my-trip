package by.andrlis.planmytrip.converter;

import by.andrlis.planmytrip.entity.ContentSourceType;

import javax.persistence.*;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ContentSourceTypeConverter implements AttributeConverter<ContentSourceType, String> {

    @Override
    public String convertToDatabaseColumn(ContentSourceType contentSourceType) {
        if (contentSourceType == null) {
            return null;
        }
        return contentSourceType.toString();
    }

    @Override
    public ContentSourceType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        return Stream.of(ContentSourceType.values())
                .filter(c -> c.toString().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}