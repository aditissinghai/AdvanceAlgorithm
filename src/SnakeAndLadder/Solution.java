package SnakeAndLadder;

import java.util.*;


public class Solution {
    private static int MAX_ROLL = 6;
    private static int BOARD_LEN = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int board[] = new int[BOARD_LEN];

        int testCases = sc.nextInt();

        for (int t = 0; t < testCases; t++) {
            int n = sc.nextInt();

            Arrays.fill(board, -1);

            for (int i = 0; i < n; i++) {
                int start = sc.nextInt()-1;
                int end = sc.nextInt()-1;
                board[start] = end;
            }

            int m = sc.nextInt();

            for (int i = 0; i < m; i++) {
                int start = sc.nextInt()-1;
                int end = sc.nextInt()-1;
                board[start] = end;
            }

            int result = quickestWayUp(board);
            System.out.println(result);
        }
    }

    private static int quickestWayUp(int[] board) {

        boolean visited[] = new boolean[BOARD_LEN];

        Queue<Entry> queue = new LinkedList<>();
        // Initial location to start the game
        Entry e = new Entry(0,0);
        visited[0] = true;
        queue.add(e);

        // Start the game //BFS from vertex 0
        while(queue.size()!=0) {
            e = queue.remove();
            if(e.v == BOARD_LEN-1) {
                break;
            }

            // Otherwise dequeue the front vertex and
            // enqueue its adjacent vertices (or cell
            // numbers reachable through a dice throw)

            for(int j = e.v+1; j <= (e.v+MAX_ROLL) && j < BOARD_LEN; j++) {
                if(!visited[j]) {
                    int dist = e.moves + 1;
                    visited[j] = true;

                    int v = j;
                    if(board[j] != -1) {
                        v = board[j];
                    }

                    Entry newEntry = new Entry(v, dist);
                    queue.add(newEntry);
                }
            }
        }

        // We reach here when 'e' has last vertex
        // return the distance of vertex in 'e'
        return e.moves;
    }

    static class Entry
    {
        int v;// Vertex number
        int moves;// Distance of this vertex from source

        public Entry(int v, int moves) {
            this.v = v;
            this.moves = moves;
        }
    }

}
