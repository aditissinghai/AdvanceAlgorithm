package RustAndMurderer;

import java.util.*;

class Solution{

    public static ArrayList<Integer>[] adj;
    public static int[] dist;
    public static int n;

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int testCases=sc.nextInt();

        for(int t = 0; t < testCases; t++) {
            n = sc.nextInt();
            int m = sc.nextInt();

            adj = new ArrayList[n+1];
            dist = new int[n+1];

            for(int i = 1; i <= n; i++)
                adj[i] = new ArrayList<Integer>();

            for(int i = 1; i <= m; i++)
            {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }

            for(int i = 1; i <= n; i++) Collections.sort(adj[i]);

            int s = sc.nextInt();

            bfs(s);
            for(int i = 1; i <= n; i++)
            {
                if(i != s)
                    System.out.print(dist[i] + " ");
            }
            System.out.println();

        }

    }

    private static void bfs(int s) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        queue.add(s);

        for(int i = 1; i <= n; i++) {
            if(i != s) q1.add(i);
        }
        while( ! queue.isEmpty() )
        {
            int u = queue.poll();
            while(! q1.isEmpty())
            {
                int v = q1.poll();
                if(Collections.binarySearch(adj[u], v) >= 0) {
                    q2.add(v);
                } else {
                    if(dist[v] == 0) {
                        dist[v] = 1 + dist[u];
                        queue.add(v);
                    }
                }
            }
            while(! q2.isEmpty() ) {
                q1.add(q2.poll());
            }
        }

    }

}