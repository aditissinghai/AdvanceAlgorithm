package ReconstructItinerary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
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
