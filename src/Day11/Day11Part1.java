package Day11;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
    private final int i;
    private final int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }
}

public class Day11Part1 {
    public static int lightUps(int[][] inp, int startI, int startJ) {
        int count = 1;
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
                    // We ignore since it was made 10 already and we do not want to reset the count furthermore
                    if (inp[m][n] == 10)
                        continue;
                    // We increase if it's not 10 since it can increase more
                    inp[m][n]++;
                    // If it goes above 9, then we add the element's indices to the queue and count another octopus
                    // that glows up
                    if (inp[m][n] > 9) {
                        q.add(new Pair(m, n));
                        count++;
                    }
                }
        }

        // Return the final count
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(11).useDelimiter("");
        int[][] octopi = new int[10][10];
        long count = 0;

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

        for (int t = 0; t < 100; t++) {
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++) {
                    // If it already glows up, then we ignore it
                    if (octopi[i][j] == 10)
                        continue;
                    // Increase by 1 since we progressed 1 step
                    octopi[i][j]++;
                    // If it lights up, we need to increase its neighbors and check if they light up as well.
                    // Then, count all the lit octopi.
                    if (octopi[i][j] > 9)
                        count += lightUps(octopi, i, j);
                }

            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++)
                    // If it was lit, we reset the counter for next step.
                    if (octopi[i][j] == 10)
                        octopi[i][j] = 0;
        }

        System.out.println(count);
    }
}
