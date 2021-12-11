package Day11;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Day11Part2 {
    public static void lightUps(int[][] inp, int startI, int startJ) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startI, startJ));
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int i = p.getI();
            int j = p.getJ();

            // Check neighborhood of the current position
            for (int m = i-1; m < i+2; m++)
                for (int n = j-1; n < j+2; n++) {
                    // Make sure we do not go out of bounds
                    if (m < 0 || m > 9 || n < 0 || n > 9)
                        continue;
                    // We ignore since it was made 10 already and we do not want to check its neighbors again.
                    if (inp[m][n] == 10)
                        continue;
                    // We increase if it's not 10 since it can increase more
                    inp[m][n]++;
                    // If it goes above 9, then we add the element's indices to the queue so we can increase
                    // neighbors' glow.
                    if (inp[m][n] > 9)
                        q.add(new Pair(m, n));
                }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(11).useDelimiter("");
        int[][] octopi = new int[10][10];
        // Counter to figure out if all octopi are lit up
        int count;
        // Step
        int t = 1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++)
                octopi[i][j] = sc.nextInt();
            // New lines are 2 characters apparently
            sc.next();
            sc.next();
        }

        // For the last line, there is no new line character(s)
        for (int i = 0; i < 10; i++)
            octopi[9][i] = sc.nextInt();

        while (true) {
            count = 0;
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++) {
                    // We ignore since it was made 10 already and we do not want to reset the count furthermore
                    if (octopi[i][j] == 10)
                        continue;
                    octopi[i][j]++;
                    if (octopi[i][j] > 9)
                        lightUps(octopi, i, j);
                }

            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++)
                    // If it glows up, then count it and also reset the glow of the octopus in case all octopi are not
                    // lit yet at the same time.
                    if (octopi[i][j] == 10) {
                        count++;
                        octopi[i][j] = 0;
                    }

            // If all of them glow up (10x10 matrix), then we print out the step and stop.
            if (count == 100) {
                System.out.println(t);
                break;
            }

            // Increase the step if not all octopi glow up.
            t++;
        }
    }
}
