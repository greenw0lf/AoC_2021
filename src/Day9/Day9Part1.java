package Day9;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day9Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(9).useDelimiter("");

        int[][] map = new int[100][100];
        int risk = 0;

        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 100; j++)
                map[i][j] = sc.nextInt();
            // For some reason, new lines are made of 2 characters?
            sc.next();
            sc.next();
        }

        // For the last line, there is no new line character(s)
        for (int i = 0; i < 100; i++)
            map[99][i] = sc.nextInt();

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++) {
                // We remember the index of the smallest value in the neighborhood
                int minI = i+j;
                if (i-1 >= 0)
                    if (map[i-1][j] <= map[i][j])
                        minI = i+j-1;
                if (j-1 >= 0)
                    if (map[i][j-1] <= map[i][j])
                        minI = i+j-1;
                if (i+1 < 100)
                    if (map[i+1][j] <= map[i][j])
                        minI = i+j+1;
                if (j+1 < 100)
                    if (map[i][j+1] <= map[i][j])
                        minI = i+j+1;
                // If index has not changed (it can be either i+j, 1 higher, or 1 lower), then we add it to the risk
                if (minI == i+j)
                    risk += (map[i][j] + 1);
            }

        System.out.println(risk);
    }
}
