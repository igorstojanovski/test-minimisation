package co.igorski.utility;

import co.igorski.model.TestSuite;

import java.io.IOException;

public interface TestSuiteLoader {
    TestSuite load(String filePath) throws IOException;
}
