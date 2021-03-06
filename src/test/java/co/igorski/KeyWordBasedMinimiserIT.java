package co.igorski;

import co.igorski.model.TestSuite;
import co.igorski.similarity.JaccardValuesCalculator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

class KeyWordBasedMinimiserIT {

    @Test
    protected void shouldMinimiseSuite() throws IOException {
        KeyWordBasedMinimiser keyWordBasedMinimiser = new KeyWordBasedMinimiser(new JaccardValuesCalculator());
        ObjectMapper objectMapper = new ObjectMapper();
        TestSuite testSuite = objectMapper.readValue(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("suite.json")), TestSuite.class);
        keyWordBasedMinimiser.minimise(testSuite, 6);
        keyWordBasedMinimiser.minimise(testSuite, 5);
        keyWordBasedMinimiser.minimise(testSuite, 4);
        keyWordBasedMinimiser.minimise(testSuite, 3);
        keyWordBasedMinimiser.minimise(testSuite, 2);
    }
}