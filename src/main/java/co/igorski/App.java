package co.igorski;

import co.igorski.similarity.JaccardValuesCalculator;
import co.igorski.utility.JsonLoader;
import co.igorski.utility.TestSuiteLoader;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        TestSuiteMinimiser testSuiteMinimiser = new KeyWordBasedMinimiser(new JaccardValuesCalculator());
        TestSuiteLoader testSuiteLoader = new JsonLoader();

        testSuiteMinimiser.minimise(testSuiteLoader.load("suite.json"), 6);
    }
}
