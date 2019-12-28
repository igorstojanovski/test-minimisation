package co.igorski.utility;

import co.igorski.model.TestSuite;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

public class JsonLoader implements TestSuiteLoader {
    public TestSuite load(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath)), TestSuite.class);
    }
}
