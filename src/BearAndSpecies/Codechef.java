package BearAndSpecies;

/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{

    private static List<Long> result = new ArrayList<>();
    private static final long MOD = 1000000007;
    private static int numCombinations = 2;

    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // Number of test cases

        for(int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());    // Size of the grid
            String[] matrix = new String[N];

            for(int j = 0; j < N; j ++) {
                matrix[j] = br.readLine();
            }

            numCombinations = 2;
            result.add(findSolution(matrix));
        }

        for(Long i : result) {
            System.out.println(i);
        }
    }

    private static long findSolution(String[] matrix) {

        int len = matrix.length;
        long possibility = 1;
        boolean visited[][] = new boolean[len][len];

        for(int row = 0; row < len; row++) {
            for(int index = 0; index < matrix[row].length(); index++) {
                if(!visited[row][index]) {      // check if row,col in the grid is not visited before, then proceed.

                    char curChar = matrix[row].charAt(index);   // check validity of all characters except '.'

                    // check validity for a bear to be grizzly
                    if(curChar == 'G' && findSingleNeighbourExists(curChar, row, index, matrix)) {
                        return 0;
                    } // check validity for a bear to be polar or brown
                    else if(curChar == 'P' || curChar == 'B')
                    {
                        if(findNonSimilarNeighbours(curChar, row, index, matrix, visited))
                        return 0;

                    }
                    else if(curChar == '?') {
                           if(!findSingleNeighbourExists(curChar, row, index, matrix)) {
                               possibility =   ( (possibility % MOD) * (3 % MOD) ) % MOD;  // According to distributive property of modulo (to avoid overflow of value)
                           }else {
                               int val = evaluatePossibilitiesForBears(row, index, matrix, visited, '?'); // Number of possible values '?' can hold
                               if(val == 0) {
                                   return 0;
                               }

                               //Independent possibilities * current possibilities
                               possibility = ( (possibility % MOD) * (val % MOD) ) % MOD; // According to distributive property of modulo (to avoid overflow of value)
                           }

                    }

                }
            }
        }

        return possibility;
    }

    private static int evaluatePossibilitiesForBears(int row, int col, String[] matrix, boolean[][] visited, char found) {

        if(!visited[row][col] && matrix[row].charAt(col)!='.') {
            visited[row][col] = true;

            if(matrix[row].charAt(col) == 'G') {
                return 0;
            }else if(matrix[row].charAt(col) == '?') {
                // dont check around
            }else if (matrix[row].charAt(col) == 'P') {
                if(found == '?') {
                    found = 'P';
                }else if(found != 'P') {
                    return 0;
                }
                numCombinations = 1;
            } else if(matrix[row].charAt(col) == 'B') {
                if(found == '?') {
                    found = 'B';
                }else if(found != 'B') {
                    return 0;
                }
                numCombinations = 1;
            }

            //check for top element
            if(row - 1 >= 0) {
                evaluatePossibilitiesForBears(row-1, col, matrix, visited, found);
            }

            // check bottom for an element
            if(row + 1 < matrix.length) {
                evaluatePossibilitiesForBears(row+1, col, matrix, visited, found);
            }

            // check left for an element
            if(col - 1 >= 0) {
                evaluatePossibilitiesForBears( row, col-1, matrix, visited, found);
            }

            // check right for an element
            if(col + 1 < matrix.length) {
                evaluatePossibilitiesForBears( row, col+1, matrix, visited, found);
            }

        }

        return numCombinations;
    }

    private static boolean findNonSimilarNeighbours(char curChar, int row, int col, String[] matrix, boolean[][] visited) {

        if(!visited[row][col]) {
            visited[row][col] = true;

            // check for neighbours if the type is the same
            if( matrix[row].charAt(col)!='?' &&  matrix[row].charAt(col)!=curChar) {
                return true;
            }

            if(row - 1 >= 0 && matrix[row-1].charAt(col)!='.') {
                findNonSimilarNeighbours(curChar, row-1, col, matrix, visited);
            }

            // check bottom for an element
            if(row + 1 < matrix.length && matrix[row+1].charAt(col)!='.') {
                findNonSimilarNeighbours(curChar, row+1, col, matrix, visited);
            }

            // check left for an element
            if(col - 1 >= 0 && matrix[row].charAt(col-1)!='.') {
                findNonSimilarNeighbours(curChar, row, col-1, matrix, visited);
            }

            // check right for an element
            if(col + 1 < matrix.length && matrix[row].charAt(col+1)!='.') {
                findNonSimilarNeighbours(curChar, row, col+1, matrix, visited);
            }
            return false;
        }

        return false;
    }

    private static boolean findSingleNeighbourExists(char curChar, int row, int col, String[] matrix) {

        boolean flag = false;

        // check top for an element
        if(row - 1 >= 0 && matrix[row-1].charAt(col) != '.') {
            flag = true;
        }

        // check bottom for an element
        if(row + 1 < matrix.length && matrix[row+1].charAt(col) != '.') {
            flag = true;
        }

        // check left for an element
        if(col - 1 >= 0 && matrix[row].charAt(col-1)!='.') {
            flag = true;
        }

        // check right for an element
        if(col + 1 < matrix.length && matrix[row].charAt(col+1)!='.') {
            flag = true;
        }

        return flag;
    }
}
