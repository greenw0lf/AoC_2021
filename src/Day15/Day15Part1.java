package Day15;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day15Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(15).useDelimiter("");
        int[][] input = new int[100][100];
        int[][] accumulator = new int[100][100];

        // Read input except last line because of the new line character missing from the last line
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 100; j++)
                input[i][j] = Integer.parseInt(sc.next());
            // Skip new line
            sc.next();
            sc.next();
        }

        // Read last line
        for (int j = 0; j < 100; j++)
            input[99][j] = Integer.parseInt(sc.next());

        // Create accumulator with a very high value, but not max integer because it can overflow when we check
        // below or to the right.
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                accumulator[i][j] = Integer.MAX_VALUE / 2;

        // The start point should be 0 since we will ignore it (we start from here so we do not enter it).
        accumulator[0][0] = 0;

        // Dynamic programming algorithm (run 100x100 times to make sure the result propagates accordingly)
        for (int k = 0; k < 10000; k++)
            for (int i = 0; i < 100; i++)
                for (int j = 0; j < 100; j++) {
                    // We ignore the first element
                    if (i == 0 && j == 0)
                        continue;
                    // If we do not go out of bounds on the left, we check if
                    // left neighbor accumulator + risk level at that position < accumulator at the current position
                    if (i != 0)
                        accumulator[i][j] = Math.min(accumulator[i - 1][j] + input[i][j], accumulator[i][j]);
                    // Same check, but for above neighbor
                    if (j != 0)
                        accumulator[i][j] = Math.min(accumulator[i][j - 1] + input[i][j], accumulator[i][j]);
                    // Right neighbor
                    if (i < 99)
                        accumulator[i][j] = Math.min(accumulator[i + 1][j] + input[i][j], accumulator[i][j]);
                    // Below neighbor
                    if (j < 99)
                        accumulator[i][j] = Math.min(accumulator[i][j + 1] + input[i][j], accumulator[i][j]);
                }

        // Our result will be the last element of the accumulator matrix (the bottom right, our destination)
        System.out.print(accumulator[99][99]);
    }
}
