package co.igorski;

import co.igorski.model.TestSuite;

public interface TestSuiteMinimiser {
    TestSuite minimise(TestSuite testSuite, int suiteSize);
}
