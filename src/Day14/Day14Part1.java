package Day14;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day14Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(14);
        // Read the polymer template
        String inp = sc.next();
        // List of pair insertions
        HashMap<String, String> mappings = new HashMap<>();
        // Number of appearances for each letter
        HashMap<Character, Integer> buckets = new HashMap<>();

        // Read the insertions
        while (sc.hasNext()) {
            String key = sc.next();
            sc.next();
            String value = sc.next();
            mappings.put(key, value);
        }

        String output = null;

        // Steps counter
        for (int t = 0; t < 10; t++) {
            output = "";
            // Convert to char array for easier processing
            char[] temp = inp.toCharArray();
            for (int i = 0; i < temp.length - 1; i++) {
                // Output gets the first letter of the pair
                output += temp[i];
                // Build the pair string
                String k = "";
                k += temp[i];
                k += temp[i+1];
                // Find the mapping of the pair and add it to the result
                output += mappings.get(k);
            }
            // Add the last letter as well
            output += temp[temp.length - 1];
            // Reset input to the output's value to process for next step
            inp = output;
        }

        char[] outputArr = output.toCharArray();

        // Counting each letter's number of times it appears in the polymer
        for (char lol: outputArr) {
            // If it's not a bucket yet, add one with value 1
            if (!buckets.containsKey(lol))
                buckets.put(lol, 1);
            // Otherwise, we can increase the current bucket by one
            else
                buckets.put(lol, buckets.get(lol) + 1);
        }

        System.out.println(Collections.max(buckets.values()) - Collections.min(buckets.values()));
    }
}
