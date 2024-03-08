import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.lang.String;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

import spellcheck_algorithms.Jaccard;
import spellcheck_algorithms.Levenshtein;

public class CS1003P1 {

    

    public static void main(String[] args) {

  
        /* All code surrounded in try/catch to catch the potential IO error */
        try {
        
        /* Setting up the program by reading the dictionary file and initialising the reader for input */
        List <String> Dictionary = Files.readAllLines(Paths.get("words_alpha.txt"));
        Scanner reader = new Scanner(System.in);

        /* Splits the sentence into individual words (I have found split to be easier than using a tokenizer personally)
         * The dictionary has no punctuation, neither should the words it checks against!
         */
        String[] words = reader.nextLine().replaceAll("[^a-zA-Z ]", "").split("\\s+");
        /* New line for better separation when printing output */
        System.out.println();
        /* Iterates through each word in the sentence, and prints them directly to output if they are contained in the dictionary */
        for (String word : words){
            if (Dictionary.contains(word.toLowerCase())){
                System.out.print(word + " ");
            }
            /* For words not contained in the dictionary, the Jaccard and Levenshtein similarity are calculated with each word in the dictionary */
            else if (word.length() > 0) {
                Map<String, Double> jaccardScoreMap = new HashMap<>();
                Map<String, Double> levScoreMap = new HashMap<>();
                /* Array for storing correction suggestions */
                List<String>  suggestions = new ArrayList<>();
                /* Stores the scores for each word */
                for (String entry: Dictionary){
                        jaccardScoreMap.put(entry, Jaccard.getJaccardSimilarity(word, entry));
                        levScoreMap.put(entry, Levenshtein.calculateLevScore(word, entry));   
                        }
                /* Adds the suggestions to the array */
                suggestions.add(Collections.max(jaccardScoreMap.entrySet(), Map.Entry.comparingByValue()).getKey());
                suggestions.add(Collections.max(levScoreMap.entrySet(), Map.Entry.comparingByValue()).getKey()); 
                /* Prints the jaccard suggestion if its score is greater than or equal to the levenshtein one or above 0.5, otherwise prints the levenshtein suggestion */
                if (Collections.max(jaccardScoreMap.entrySet(), Map.Entry.comparingByValue()).getValue() > Collections.max(levScoreMap.entrySet(), Map.Entry.comparingByValue()).getValue() || Collections.max(jaccardScoreMap.entrySet(), Map.Entry.comparingByValue()).getValue() >= 0.5 ){
                    System.out.print(suggestions.get(0) + " ");
                }
                else{
                    System.out.print(suggestions.get(1) + " ");
                }
                
    
                }    
            }
            
    
    }

    catch(IOException e) {System.out.println("IO Error reading the file! " + e);}

    }
}
 

    

