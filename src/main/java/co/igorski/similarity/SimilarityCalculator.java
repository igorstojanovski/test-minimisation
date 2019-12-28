package co.igorski.similarity;

import java.util.Map;
import java.util.Set;

public interface SimilarityCalculator {
    Map<String, Map<String, Float>> calculateSimilarity(Map<String, Set<String>> encodedTests);
}
