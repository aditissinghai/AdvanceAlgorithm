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
    private static int rowD[] = {-1, 0, 0, 1};
    private static int colD[] = {0, -1, 1, 0};

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
            for(int index = 0; index < len; index++) {
                if(check(row, index, matrix) && !visited[row][index]) {      // check if row,col in the grid is not visited before, then proceed.

                    char curChar = matrix[row].charAt(index);   // check validity of all characters except '.'

                    // check validity for a bear to be grizzly
                    if(curChar == 'G') {
                        if(!findSingleNeighbourExists(curChar, row, index, matrix))
                            return 0;
                        continue;
                    } // check validity for a bear to be polar or brown
                    else if(curChar == 'P' || curChar == 'B')
                    {
                        if(!findNonSimilarNeighbours(curChar, row, index, matrix, visited))
                            return 0;
                        continue;
                    }
                    else if(curChar == '?') {
                           if(findSingleNeighbourExists(curChar, row, index, matrix)) {
                               possibility =   ( (possibility % MOD) * (3 % MOD) ) % MOD;  // According to distributive property of modulo (to avoid overflow of value)
                               continue;
                           }
                           int val = evaluatePossibilitiesForBears(row, index, matrix, visited); // Number of possible values '?' can hold
                           if(val == 0) {
                               return 0;
                           }

                           //Independent possibilities * current possibilities
                           possibility = ( (possibility % MOD) * (val % MOD) ) % MOD; // According to distributive property of modulo (to avoid overflow of value)
                    }

                }
            }
        }

        return possibility;
    }

    private static int evaluatePossibilitiesForBears(int row, int col, String[] matrix, boolean[][] visited) {

        LinkedList<Coordinates> queue = new LinkedList<>();
        boolean PFound = false;
        boolean BFound = false;
        boolean allQuestions = true;

        queue.add(new Coordinates(row, col));
        visited[row][col] = true;

        while(queue.size() !=0 ){
            Coordinates c = queue.poll();
            for(int i=0; i < 4; i++) {
                int newRow = c.row + rowD[i];
                int newCol = c.col + colD[i];
                if(check(newRow, newCol, matrix) && !visited[newRow][newCol])
                {
                    visited[newRow][newCol] = true;
                    queue.add(new Coordinates(newRow, newCol));

                    if(matrix[newRow].charAt(newCol) == '?')
                        continue;

                    if(matrix[newRow].charAt(newCol) == 'G')
                        return 0;


                    if(matrix[newRow].charAt(newCol) == 'P')
                    {
                        if(BFound)
                            return 0;

                        PFound = true;
                        allQuestions = false;
                    }

                    if(matrix[newRow].charAt(newCol) == 'B')
                    {
                        if(PFound)
                            return 0;

                        BFound = true;
                        allQuestions = false;
                    }
                }
            }
        }

        if(allQuestions)
            return 2;

        return 1;
    }


    private static boolean findNonSimilarNeighbours(char curChar, int row, int col, String[] matrix, boolean[][] visited) {

        LinkedList<Coordinates> queue = new LinkedList<>();

        queue.add(new Coordinates(row, col));
        visited[row][col] = true;

        while(queue.size() !=0 ){

            Coordinates c = queue.poll();

            for(int i=0; i < 4; i++){
                int newRow = c.row + rowD[i];
                int newCol = c.col + colD[i];

                if(check(newRow, newCol, matrix) && !visited[newRow][newCol]){
                    visited[newRow][newCol] = true;
                    queue.add(new Coordinates(newRow, newCol));
                    if(matrix[newRow].charAt(newCol) != '?' && matrix[newRow].charAt(newCol) != curChar)
                        return false;
                }
            }
        }

        return true;

    }

    private static boolean findSingleNeighbourExists(char curChar, int row, int col, String[] matrix) {
        for(int i=0; i < 4; i++){
            int newRow = row + rowD[i];
            int newCol = col + colD[i];
            if(newRow >= 0 && newRow < matrix.length && newCol >=0 && newCol < matrix.length && matrix[newRow].charAt(newCol) != '.')
                return false;
        }

        return true;

    }

    private static boolean check(int row, int col, String matrix[]){
        if(row >= 0 && row < matrix.length && col >=0 && col < matrix.length && matrix[row].charAt(col) != '.')
            return true;

        return false;
    }

    static class Coordinates {
        public int row;
        public int col;
        public Coordinates(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
