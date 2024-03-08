package spellcheck_algorithms;


import java.lang.String;
import java.util.Set;
import java.util.HashSet;
/* Final as I dont want any other classes to inherit from here */
public final class Jaccard {
    /* Private constructor so a "jaccard" object can never be created, as this is simply a class for methods */
    private Jaccard(){}

    /* Calculates the set of bigrams by taking the letters in a given word in groups of two. A hashset is used to ensure 
     * only unique bigrams are stored.
     */
    static private Set<String> getUniqueBigrams(String word) {
        word = "^" + word + "$";
        Set<String> bigrams = new HashSet<>();
        for (int i = 0; i < word.length() - 1; i++) {
            bigrams.add(word.substring(i, i + 2));
        }
        return bigrams;
    }

    /* Straightforward intersection of two sets. Starts with a set, and then removes all of its elements which are not also
     * contained in a second set.
     */
    static private Set<String> getIntersection(Set<String> setOne, Set<String> setTwo){
        Set<String> intersection = new HashSet<>(setOne);
        intersection.retainAll(setTwo);
        return intersection;

    }

    /* Straightforward union of two sets. Starts with a set, and then adds all elements from a second set to it. This works, as all
     * elements of a set need to be unique to count as an individual element.
     */
    static private Set<String> getUnion(Set<String> setOne, Set<String> setTwo){
        Set<String> union = new HashSet<>(setOne);
        union.addAll(setTwo);
        return union;
    }

    /* Performs the Jaccard calculation */
    static public double getJaccardSimilarity(String wordOne, String wordTwo){
        double jaccardScore = ((double)getIntersection(getUniqueBigrams(wordOne), getUniqueBigrams(wordTwo)).size()) / (getUnion(getUniqueBigrams(wordOne),getUniqueBigrams(wordTwo)).size());
        return jaccardScore;
    }
    



}