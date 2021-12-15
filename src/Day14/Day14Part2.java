package Day14;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day14Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(14);
        // Read the polymer template
        String inp = sc.next();
        // List of pair insertions
        HashMap<String, String> mappings = new HashMap<>();
        // List of current pairs in the polymer
        HashMap<String, Long> pairs = new HashMap<>();
        // Number of appearances for each letter
        HashMap<Character, Long> resultArr = new HashMap<>();

        // Read the insertions
        while (sc.hasNext()) {
            String key = sc.next();
            sc.next();
            String value = sc.next();
            // Remember the mapping
            mappings.put(key, value);
            // Initialize the number of appearances for each kind of pair possible
            pairs.put(key, 0L);
        }

        // Used later to reset counters for the temporary array
        HashMap<String, Long> zeros = new HashMap<>(pairs);

        // Give the template's pairs to the number of appearances array
        char[] input = inp.toCharArray();
        String k = null;
        for (int i = 0; i < input.length - 1; i++) {
            k = "";
            k += input[i];
            k += input[i + 1];
            pairs.put(k, pairs.get(k) + 1);
        }

        // Steps counter
        for (int t = 0; t < 40; t++) {
            // Start with no appearances in our "after 1 step" array
            HashMap<String, Long> tempArr = new HashMap<>(zeros);

            // For each pair in the input before doing the step, we get its mapping, then create 2 pairs accordingly,
            // one with the first letter of pair + mapping, and the other with mapping + second letter of pair
            // (as if we are inserting the mapping between the pair's characters)
            for (String key: pairs.keySet()) {
                String pair1 = key.charAt(0) + mappings.get(key);
                String pair2 = mappings.get(key) + key.charAt(1);
                // Make sure to add to the previous count to not reset it
                tempArr.put(pair1, tempArr.get(pair1) + pairs.get(key));
                tempArr.put(pair2, tempArr.get(pair2) + pairs.get(key));
            }

            // Once we are done processing this step, we replace the array before the step with the current one
            pairs = new HashMap<>(tempArr);
        }

        for (String key: pairs.keySet()) {
            // Take the first character in the pair and count it
            if (!resultArr.containsKey(key.charAt(0)))
                resultArr.put(key.charAt(0), pairs.get(key));
            else
                resultArr.put(key.charAt(0), resultArr.get(key.charAt(0)) + pairs.get(key));
            // Take the second character and count it
            if (!resultArr.containsKey(key.charAt(1)))
                resultArr.put(key.charAt(1), pairs.get(key));
            else
                resultArr.put(key.charAt(1), resultArr.get(key.charAt(1)) + pairs.get(key));
        }

        // Dividing by 2 because pairs can share the same letter, but we count it twice
        // We need to compensate by either dividing by 2 when it's an even number (because that means that the character
        // we counted does not appear at the beginning or end of the string)
        // and if it's odd, we integer divide by 2 then add 1 (the character in question might appear at the very end/
        // beginning)
        resultArr.replaceAll((c, v) -> v % 2 == 1 ? v / 2 + 1 : v / 2);

        System.out.println(Collections.max(resultArr.values()) - Collections.min(resultArr.values()));
    }
}
