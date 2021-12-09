package Day6;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(6);

        String allNum = sc.next();
        String[] numb = allNum.split(",");
        ArrayList<Integer> lanternfish = new ArrayList<>();

        for (String s : numb) lanternfish.add(Integer.parseInt(s));

        // The 80 days cycle
        for (int i = 0; i < 80; i++) {
            // Decrement each lantern fish
            for (int j = 0; j < lanternfish.size(); j++) {
                int ues = lanternfish.get(j);
                ues--;
                // If it got to -1, then we need to generate a new lantern fish and reset counter
                if (ues == -1) {
                    // Setting it to 9 because it will be subtracted later on in the same day anyways, making it
                    // 8 as it should be according to problem description.
                    lanternfish.add(9);
                    // Setting this to 6 because it will not be subtracted until next day. Also, reset counter
                    ues = 6;
                }
                lanternfish.set(j, ues);
            }
        }

        System.out.println(lanternfish.size());
    }
}
