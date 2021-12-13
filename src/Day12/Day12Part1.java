package Day12;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12Part1 {
    public static int findPaths(int i,
                                ArrayList<String> nodes,
                                ArrayList<ArrayList<String>> neighbors,
                                ArrayList<String> path) {
        int res = 0;
        ArrayList<String> currNeighbors = neighbors.get(i);

        for (String neigh: currNeighbors) {
            // If we can reach the end, we stop and count a path
            if (neigh.equals("end")) {
                res++;
                continue;
            }
            // If the start node is a neighbor
            if (neigh.equals("start"))
                continue;
            // If either the path does not contain the cave, or if the cave is large
            if (!path.contains(neigh) || neigh.equals(neigh.toUpperCase())) {
                // We add the cave to the path
                ArrayList<String> nextPath = new ArrayList<>(path);
                nextPath.add(neigh);
                // Then find the number of paths of that cave and add it to the result
                res += findPaths(nodes.indexOf(neigh), nodes, neighbors, nextPath);
            }
        }

        return res;
    }

    public static void main (String[] args) throws FileNotFoundException {
        // MAKE SURE TO CHANGE THE DELIMITER ACCORDING TO YOUR OS
        // Windows - current one
        // Linux - "-|\\n"
        // MacOS - "-|\\r"
        Scanner sc = ReadingOfFiles.readFile(12).useDelimiter("-|\\r\\n");
        // List of nodes
        ArrayList<String> nodes = new ArrayList<>();
        // List of neighbors for each node. The list has the same index as the node
        ArrayList<ArrayList<String>> neighbors = new ArrayList<>();

        while (sc.hasNext()) {
            // Take the first pair of nodes
            String node1 = sc.next();
            String node2 = sc.next();

            // If the first node doesn't exist, add entries in both the list of nodes and neighbors
            if (!nodes.contains(node1)) {
                nodes.add(node1);
                neighbors.add(new ArrayList<>());
            }
            // Add the second node as a neighbor to the first one
            neighbors.get(nodes.indexOf(node1)).add(node2);

            // Vice-versa for the 2nd node
            if (!nodes.contains(node2)) {
                nodes.add(node2);
                neighbors.add(new ArrayList<>());
            }
            neighbors.get(nodes.indexOf(node2)).add(node1);
        }

        // Start searching for all the paths, starting with the "start" node
        System.out.println(findPaths(nodes.indexOf("start"), nodes, neighbors, new ArrayList<>()));
    }
}
