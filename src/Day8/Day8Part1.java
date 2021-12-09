package Day8;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day8Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(8);
        // This will be our input every time we read.
        String temp;
        int res = 0;

        while (sc.hasNext()) {
            // Read the digit panels, but ignore because we do not care about them in this part.
            for (int i = 0; i < 10; i++)
                temp = sc.next();

            // Read the |
            temp = sc.next();

            // The output we are interested to analyze.
            for (int i = 0; i < 4; i++) {
                temp = sc.next();
                // All the other number we do not care about have either 5 or 6 lines when represented.
                // So, we increment whenever we do not have those numbers, which are all the other lengths.
                if (temp.length() != 5 && temp.length() != 6)
                    res++;
            }
        }

        System.out.println(res);
    }
}
