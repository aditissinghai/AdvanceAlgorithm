package FriendCircles;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        int N = 3;
        int[][] M = new int[N][N];
        putOne(M, 0, 0);
        putOne(M, 0, 1);
        putOne(M, 1, 0);
        putOne(M, 1, 1);
        putOne(M, 2, 2);

        System.out.println(findCircleNum(M));

    }

    private static void putOne(int[][] m, int row, int col) {
        m[row][col] = 1;
    }

    public static int findCircleNum(int[][] M) {

        if(M.length == 0 || M[0].length == 0) {
            return 0;
        }

        int len = M.length;

        UnionFind uf = new UnionFind(len);

        for(int row = 0; row < len; row++) {
            for(int col = row+1; col < len; col++) {
                // assign parent to the edge row, col
                if(M[row][col] == 1)
                    uf.union(row, col);
            }
        }

        int circles = 0;
        for(int i = 0; i < len; i++) {
            if(uf.parent[i] == -1) {
                circles++;
            }
        }
        return circles;
    }


    static class UnionFind {
        public int[] parent;

        public UnionFind(int len) {
            parent = new int[len];
            Arrays.fill(parent, -1);
        }
        // check if val1 and val2 have same parents, if not assign parent.
        public void union(int val1, int val2) {
            int root1 = find(val1);
            int root2 = find(val2);

            if(root1 != root2) {
                parent[root1] = root2;
            }
        }

        public int find(int val) {
            if(parent[val] == -1) {
                return val;
            }
            // finds the highest value of parent available
            return find(parent[val]);
        }


    }

}