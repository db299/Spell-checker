package spellcheck_algorithms;

public final class Levenshtein{
    private Levenshtein(){}; 

    public static double calculateLevScore(String wordOne, String wordTwo){
        int[][] levMatrix = new int[wordOne.length()+1][wordTwo.length()+1];
        /* Initialises first rows of levenshtein matrix for the 2 words */
        for(int i = 0; i <= wordOne.length(); i++){
            levMatrix[i][0] = i;
        }
        for(int i = 0; i <= wordTwo.length(); i++){
            levMatrix[0][i] = i;
        }
        /* Calculates the rest of the rows */
        for(int i = 1; i <= wordOne.length(); i++){
            for(int j =1; j <= wordTwo.length(); j++){
                /* Cost is 0 if the 2 characters are equal, 1 if they are not */
                int cost = (wordOne.charAt(i-1) == wordTwo.charAt(j-1)) ? 0 : 1;
                /* Populates the levenshtein matrix */
                levMatrix[i][j] = Math.min(Math.min(levMatrix[i-1][j] + 1, levMatrix[i][j-1] + 1), levMatrix[i-1][j-1] + cost);

            }
        }

        /* Scales the levenshtein distance to a score between 0 and 1 as explained in report */
        return 1/levMatrix[wordOne.length()][wordTwo.length()];
            
        }
        
        
    }

