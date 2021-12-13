package Day12;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day12Part2 {
    public static int findPaths(int i,
                                ArrayList<String> nodes,
                                ArrayList<ArrayList<String>> neighbors,
                                HashMap<String, Integer> path) {
        int res = 0;
        ArrayList<String> currNeighbors = neighbors.get(i);

        for (String neigh: currNeighbors) {
            if (neigh.equals("end")) {
                res++;
                continue;
            }

            if (neigh.equals("start"))
                continue;

            // Keep track of number of appearances of the different caves throughout the path to find if a small cave
            // was already visited twice.
            HashMap<String, Integer> nextPath = new HashMap<>(path);
            // If neighbor is not part of path yet
            if (!path.containsKey(neigh)) {
                nextPath.put(neigh, 1);
                res += findPaths(nodes.indexOf(neigh), nodes, neighbors, nextPath);
                continue;
            }

            // Check if a small cave was already covered twice
            boolean two = false;
            for (String key: nextPath.keySet())
                if (key.equals(key.toLowerCase()) && nextPath.get(key) == 2)
                    two = true;
            // If every small cave in the path was traversed only once, then we can increment the current neighbor's
            // number of traversals.
            if (!two || neigh.equals(neigh.toUpperCase())) {
                nextPath.put(neigh, nextPath.get(neigh) + 1);
                res += findPaths(nodes.indexOf(neigh), nodes, neighbors, nextPath);
            }
        }

        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // MAKE SURE TO CHANGE THE DELIMITER ACCORDING TO YOUR OS
        // Windows - current one
        // Linux - "-|\\n"
        // MacOS - "-|\\r"
        Scanner sc = ReadingOfFiles.readFile(12).useDelimiter("-|\\r\\n");
        ArrayList<String> nodes = new ArrayList<>();
        ArrayList<ArrayList<String>> neighbors = new ArrayList<>();

        while (sc.hasNext()) {
            String node1 = sc.next();
            String node2 = sc.next();

            if (!nodes.contains(node1)) {
                nodes.add(node1);
                neighbors.add(new ArrayList<>());
            }
            neighbors.get(nodes.indexOf(node1)).add(node2);

            if (!nodes.contains(node2)) {
                nodes.add(node2);
                neighbors.add(new ArrayList<>());
            }
            neighbors.get(nodes.indexOf(node2)).add(node1);
        }

        System.out.println(findPaths(nodes.indexOf("start"), nodes, neighbors, new HashMap<>()));
    }
}
