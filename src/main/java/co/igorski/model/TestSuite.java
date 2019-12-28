package co.igorski.model;

import java.util.HashMap;
import java.util.Map;

public class TestSuite {

    Map<String, TestEncoding> testEncodings = new HashMap<>();

    public void addTestEncoding(TestEncoding testEncoding) {
        testEncodings.put(testEncoding.getTestId(), testEncoding);
    }

    public Map<String, TestEncoding> getTestEncodings() {
        return testEncodings;
    }
}
