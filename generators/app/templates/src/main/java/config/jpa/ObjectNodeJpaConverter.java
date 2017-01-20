package <%=groupId%>.config.jpa;

import java.io.IOException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Converter
public class ObjectNodeJpaConverter implements AttributeConverter<ObjectNode, String>{
    static private final Logger log = LoggerFactory.getLogger(ObjectNodeJpaConverter.class);

    @Override
    public String convertToDatabaseColumn(ObjectNode attribute) {
        return attribute.toString();
    }

    @Override
    public ObjectNode convertToEntityAttribute(String dbData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(dbData, ObjectNode.class);
        } catch (IOException e) {
            String message = String.format("Could not deserialize value from db: %s", dbData);
            log.error(message);
            return null;
        }
    }

}
