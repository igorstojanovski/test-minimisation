package co.igorski;

import co.igorski.model.TestEncoding;
import co.igorski.model.TestSuite;
import co.igorski.similarity.JaccardValuesCalculator;
import co.igorski.similarity.SimilarityCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class KeyWordBasedMinimiser implements TestSuiteMinimiser {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyWordBasedMinimiser.class);
    private final SimilarityCalculator similarityCalculator;
    private Map<String, Set<String>> testKeywords = new HashMap<>();

    public KeyWordBasedMinimiser(JaccardValuesCalculator jaccardValuesCalculator) {
        this.similarityCalculator = jaccardValuesCalculator;
    }

    @Override
    public Set<String> minimise(TestSuite testSuite, int suiteSize) {

        List<TestEncoding> encodings = testSuite.getTestEncodings();

        for(TestEncoding testEncoding : encodings) {
            String encoding = testEncoding.getEncoding();
            Set<String> keywords = new HashSet<>(Arrays.asList(encoding.split(", ")));
            testKeywords.put(testEncoding.getTestId(), keywords);
        }

        System.out.println("-----------------------------------");
        printCurrentJaccardValues();

        while(testKeywords.keySet().size() > suiteSize) {
            Map<String, Map<String, Float>> jaccardValues = similarityCalculator.calculateSimilarity(testKeywords);
            String idToRemove = removeMostSimilar(jaccardValues);
            testKeywords.remove(idToRemove);
        }

        System.out.println("-----------------------------------");
        printCurrentJaccardValues();

        LOGGER.info(String.valueOf(testKeywords.keySet()));
        return testKeywords.keySet();
    }

    private void printCurrentJaccardValues() {
        Map<String, Map<String, Float>> jaccardValues = similarityCalculator.calculateSimilarity(testKeywords);
        ArrayList<String> sortedKeys = new ArrayList<>(testKeywords.keySet());
        Collections.sort(sortedKeys);

        if(LOGGER.isTraceEnabled()) {
            for(String key : sortedKeys) {
                LOGGER.trace(key + " " + jaccardValues.get(key));
            }
        }
    }

    private String removeMostSimilar(Map<String, Map<String, Float>> jaccardValues) {
        Float max = 0.0F;
        String maxId = "";
        for (Map.Entry<String, Map<String, Float>> outerEntry : jaccardValues.entrySet()) {
            Map<String, Float> outerValue = outerEntry.getValue();
            String outerKey = outerEntry.getKey();

            for(Map.Entry<String, Float> innerEntry : outerValue.entrySet()) {
                if(innerEntry.getValue() > max) {
                    max = innerEntry.getValue();
                    maxId = testKeywords.get(innerEntry.getKey()).size() < testKeywords.get(outerKey).size()
                            ? innerEntry.getKey()
                            : outerKey;
                }
            }
        }

        return maxId;
    }
}
