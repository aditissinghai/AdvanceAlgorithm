package WordLadderII;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        Solution s = new Solution();
        List<List<String>> solution = s.findLadders(beginWord, endWord, wordList);

        for(List<String> l : solution) {
            for(String ans : l) {
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }

    List<List<String>> result = new ArrayList<>();
    HashMap<String, Set<String>> graph = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<String> sequence = new ArrayList<>();

        HashSet<String> dictionary = new HashSet<>(wordList);
        constructGraph(beginWord, endWord, graph, dictionary);
        constructSequence(beginWord, endWord, graph, sequence);

        return result;
    }

    private void constructSequence(String beginWord, String endWord, HashMap<String, Set<String>> graph, List<String> sequence) {
        sequence.add(beginWord);

        if(beginWord.equals(endWord)) {
            result.add(new ArrayList<>(sequence));
        } else {
            if(graph.containsKey(beginWord)) {
                Set<String> path = graph.get(beginWord);
                for(String node : path) {
                    constructSequence(node, endWord, graph, sequence);
                }
            }
        }

        sequence.remove(sequence.size()-1);
    }

    private void constructGraph(String beginWord, String endWord, HashMap<String, Set<String>> graph, HashSet<String> dictionary) {
        HashSet<String> visited = new HashSet<>();
        HashSet<String> remaining = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        remaining.add(beginWord);
        boolean end = false;

        // Applying BFS to get the neighbouring strings
        while(q.size()!=0) {

            int qCapacity = q.size();
            visited.addAll(remaining);
            remaining.clear();

            while(qCapacity!=0) {

                String cur = q.poll();
                List<String> neighbours = getAdjNeighbours(cur, dictionary);

                for(String neigh : neighbours) {

                    if(neigh.equals(endWord)) {
                        end = true;
                    }
                    if(!visited.contains(neigh)) {
                        if (!graph.containsKey(cur)) {
                            graph.put(cur, new HashSet<>());
                        }
                        graph.get(cur).add(neigh);
                    }

                    if(!visited.contains(neigh) && !remaining.contains(neigh)) {
                        q.offer(neigh);
                        remaining.add(neigh);
                    }
                }
                qCapacity--;
            }

            // This ensures shortest distance is achieved while traversing through BFS
            if(end) {
                break;
            }
        }

    }

    private List<String> getAdjNeighbours(String cur, HashSet<String> dictionary) {
        List<String> neigh = new ArrayList<>();

        char[] ch = cur.toCharArray();

        for(int index = 0; index < cur.length(); index++) {
            for(int c = 'a'; c <= 'z'; c++) {
                char curchar = ch[index];
                ch[index] = (char)c;
                String newString = new String(ch);
                if(dictionary.contains(newString) && !newString.equals(cur)) {
                    neigh.add(newString);
                }
                ch[index] = curchar;
            }
        }

        return neigh;
    }
}
