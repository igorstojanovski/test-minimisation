package co.igorski;

import co.igorski.model.TestSuite;

import java.util.Set;

public interface TestSuiteMinimiser {
    Set<String> minimise(TestSuite testSuite, int suiteSize);
}
