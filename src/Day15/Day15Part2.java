package Day15;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day15Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(15).useDelimiter("");
        int[][] input = new int[500][500];
        int[][] accumulator = new int[500][500];

        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 100; j++)
                input[i][j] = Integer.parseInt(sc.next());
            // Skip new lines
            sc.next();
            sc.next();
        }

        for (int j = 0; j < 100; j++)
            input[99][j] = Integer.parseInt(sc.next());

        // This will take 625 times more than part 1, and I was too lazy to find a more efficient implementation,
        // therefore I add a timer to check how long does it take to print out the result
        long startTime = System.nanoTime();
        // Duplicate the input according to instructions, duplicate under the input table
        for (int k = 1; k < 5; k++) {
            for (int i = 0; i < 100; i++)
                for (int j = 0; j < 100; j++)
                    // If it goes above 9, we reset to 1, otherwise we increase by 1 from the same position as table above
                    input[i + 100*k][j] = input[i + 100*(k-1)][j] + 1 > 9 ? 1 : input[i + 100*(k-1)][j] + 1;
        }

        // Duplicate, except we do it for the right and below now
        for (int x = 0; x < 5; x++)
            for (int y = 1; y < 5; y++)
                for (int i = 0; i < 100; i++)
                    for (int j = 0; j < 100; j++)
                        input[x*100 + i][y*100 + j] = input[x*100 + i][(y-1)*100 + j] + 1 > 9 ? 1
                                : input[x*100 + i][(y-1)*100 + j] + 1;

        for (int i = 0; i < 500; i++)
            for (int j = 0; j < 500; j++)
                accumulator[i][j] = Integer.MAX_VALUE/2;

        accumulator[0][0] = 0;

        for (int k = 0; k < 250000; k++) {
            for (int i = 0; i < 500; i++)
                for (int j = 0; j < 500; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    if (i != 0)
                        accumulator[i][j] = Math.min(accumulator[i - 1][j] + input[i][j], accumulator[i][j]);
                    if (j != 0)
                        accumulator[i][j] = Math.min(accumulator[i][j - 1] + input[i][j], accumulator[i][j]);
                    if (i < 499)
                        accumulator[i][j] = Math.min(accumulator[i + 1][j] + input[i][j], accumulator[i][j]);
                    if (j < 499)
                        accumulator[i][j] = Math.min(accumulator[i][j + 1] + input[i][j], accumulator[i][j]);
                }
        }
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime / 1000000000 + "s");
        System.out.println();

        System.out.print(accumulator[499][499]);
    }
}
