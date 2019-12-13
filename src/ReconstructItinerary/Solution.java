package ReconstructItinerary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        List<List<String>> s = new ArrayList<>();
        List<String> s1 = new ArrayList<>();
        s1.add("MUC");
        s1.add("LHR");
        List<String> s2 = new ArrayList<>();
        s2.add("JFK");
        s2.add("MUC");
        List<String> s3 = new ArrayList<>();
        s3.add("SFO");
        s3.add("SJC");
        List<String> s4 = new ArrayList<>();
        s4.add("LHR");
        s4.add("SFO");
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);

        Solution sol = new Solution();
        List<String> res = sol.findItinerary(s);

        for(String a : res) {
            System.out.print(a+" ");
        }
    }

    HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> ans = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> itinerary : tickets) {

            PriorityQueue<String> pq;

            if(map.containsKey(itinerary.get(0))) {
                pq = map.get(itinerary.get(0));
                pq.add(itinerary.get(1));
            }else {
                pq = new PriorityQueue<String>();
                pq.add(itinerary.get(1));
                map.put(itinerary.get(0), pq);
            }
        }

        travel("JFK");
        return ans;
    }

    public void travel(String dest) {
        while(map.containsKey(dest) && !map.get(dest).isEmpty()) {
            travel(map.get(dest).poll());
        }
        ans.add(0, dest);
    }

}
