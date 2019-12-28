package co.igorski;

import co.igorski.model.TestEncoding;
import co.igorski.model.TestSuite;

import java.util.*;

public class KeyWordBasedMinimiser implements TestSuiteMinimiser {

    private Map<String, Set<String>> testKeywords = new HashMap<>();
    private Map<String, Map<String, Integer>> intersections = new HashMap<>();
    private Map<String, Map<String, Integer>> unions = new HashMap<>();
    private Map<String, Map<String, Float>> jaccardIndex = new HashMap<String, Map<String, Float>>();

    @Override
    public TestSuite minimise(TestSuite testSuite) {

        Map<String, TestEncoding> encodings = testSuite.getTestEncodings();

        for(Map.Entry<String, TestEncoding> entry : encodings.entrySet()) {
            String encoding = entry.getValue().getEncoding();
            Set<String> keywords = new HashSet<>(Arrays.asList(encoding.split(", ")));
            testKeywords.put(entry.getKey(), keywords);
        }

        List<String> pastIds = new ArrayList<>();

        ArrayList<String> sortedKeys = new ArrayList<>(testKeywords.keySet());
        Collections.sort(sortedKeys);

        for(String outerKey : sortedKeys) {
            pastIds.add(outerKey);
            Set<String> outerSet = testKeywords.get(outerKey);

            Map<String, Integer> intersectionValues = new HashMap<>();
            Map<String, Integer> unionValues = new HashMap<>();
            Map<String, Float> jaccardValues = new HashMap<>();

            for(String innerKey : sortedKeys) {
                if(!pastIds.contains(innerKey)) {
                    Set<String> innerSet = testKeywords.get(innerKey);
                    HashSet<String> intersectionSet = new HashSet<>(outerSet);
                    intersectionSet.retainAll(innerSet);
                    intersectionValues.put(innerKey, intersectionSet.size());

                    HashSet<String> unionSet = new HashSet<>(outerSet);
                    unionSet.addAll(innerSet);
                    unionValues.put(innerKey, unionSet.size());

                    jaccardValues.put(innerKey, (float)intersectionSet.size()/(float)unionSet.size());
                }
            }

            if(!jaccardValues.isEmpty()) {
                jaccardIndex.put(outerKey, jaccardValues);
            }

            if(!intersectionValues.isEmpty()) {
                intersections.put(outerKey, intersectionValues);
                unions.put(outerKey, unionValues);
            }
        }




        for(String outerKey : sortedKeys) {
            System.out.print(outerKey + " ");
            System.out.println(jaccardIndex.get(outerKey));
        }

        return null;
    }
}
