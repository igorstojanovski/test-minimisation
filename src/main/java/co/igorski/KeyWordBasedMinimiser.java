package co.igorski;

import co.igorski.model.TestEncoding;
import co.igorski.model.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class KeyWordBasedMinimiser implements TestSuiteMinimiser {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyWordBasedMinimiser.class);
    private Map<String, Set<String>> testKeywords = new HashMap<>();

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
            Map<String, Map<String, Float>> jaccardValues = getJaccardValues();
            String idToRemove = removeMostSimilar(jaccardValues);
            testKeywords.remove(idToRemove);
        }

        System.out.println("-----------------------------------");
        printCurrentJaccardValues();

        LOGGER.info(String.valueOf(testKeywords.keySet()));
        return testKeywords.keySet();
    }

    private void printCurrentJaccardValues() {
        Map<String, Map<String, Float>> jaccardValues = getJaccardValues();
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

    private Map<String, Map<String, Float>> getJaccardValues() {
        Map<String, Map<String, Float>> jaccardIndex = new HashMap<>();
        List<String> pastIds = new ArrayList<>();

        ArrayList<String> sortedKeys = new ArrayList<>(testKeywords.keySet());
        Collections.sort(sortedKeys);

        for(String outerKey : sortedKeys) {
            pastIds.add(outerKey);
            Set<String> outerSet = testKeywords.get(outerKey);

            Map<String, Float> jaccardValues = new HashMap<>();

            for(String innerKey : sortedKeys) {
                if(!pastIds.contains(innerKey)) {
                    Set<String> innerSet = testKeywords.get(innerKey);
                    HashSet<String> intersectionSet = new HashSet<>(outerSet);
                    intersectionSet.retainAll(innerSet);

                    HashSet<String> unionSet = new HashSet<>(outerSet);
                    unionSet.addAll(innerSet);

                    jaccardValues.put(innerKey, (float)intersectionSet.size()/(float)unionSet.size());
                }
            }

            if(!jaccardValues.isEmpty()) {
                jaccardIndex.put(outerKey, jaccardValues);
            }
        }
        return jaccardIndex;
    }
}
