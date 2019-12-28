package co.igorski;

import co.igorski.model.TestEncoding;
import co.igorski.model.TestSuite;

import java.util.*;

public class KeyWordBasedMinimiser implements TestSuiteMinimiser {

    private Map<String, Set<String>> testKeywords = new HashMap<>();

    @Override
    public TestSuite minimise(TestSuite testSuite, int suiteSize) {

        Map<String, TestEncoding> encodings = testSuite.getTestEncodings();

        for(Map.Entry<String, TestEncoding> entry : encodings.entrySet()) {
            String encoding = entry.getValue().getEncoding();
            Set<String> keywords = new HashSet<>(Arrays.asList(encoding.split(", ")));
            testKeywords.put(entry.getKey(), keywords);
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

        return null;
    }

    private void printCurrentJaccardValues() {
        Map<String, Map<String, Float>> jaccardValues = getJaccardValues();
        ArrayList<String> sortedKeys = new ArrayList<>(testKeywords.keySet());
        Collections.sort(sortedKeys);

        for(String key : sortedKeys) {
            System.out.print(key + " ");
            System.out.println(jaccardValues.get(key));
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
