package co.igorski.similarity;

import java.util.*;

public class JaccardValuesCalculator implements SimilarityCalculator {
    @Override
    public Map<String, Map<String, Float>> calculateSimilarity(Map<String, Set<String>> encodedTests) {
        Map<String, Map<String, Float>> jaccardIndex = new HashMap<>();
        List<String> pastIds = new ArrayList<>();

        ArrayList<String> sortedKeys = new ArrayList<>(encodedTests.keySet());
        Collections.sort(sortedKeys);

        for(String outerKey : sortedKeys) {
            pastIds.add(outerKey);
            Set<String> outerSet = encodedTests.get(outerKey);

            Map<String, Float> jaccardValues = new HashMap<>();

            for(String innerKey : sortedKeys) {
                if(!pastIds.contains(innerKey)) {
                    Set<String> innerSet = encodedTests.get(innerKey);
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
