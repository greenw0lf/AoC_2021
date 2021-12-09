package Day5;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5Part2 {
    public static void main(String []args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(5);
        int[][] matrix = new int[1000][1000];

        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                matrix[i][j] = 0;

        int count = 0;

        while (sc.hasNext()) {
            String x1y1 = sc.next();
            int x1 = Integer.parseInt(x1y1.split(",")[0]);
            int y1 = Integer.parseInt(x1y1.split(",")[1]);
            String ignore = sc.next();
            String x2y2 = sc.next();
            int x2 = Integer.parseInt(x2y2.split(",")[0]);
            int y2 = Integer.parseInt(x2y2.split(",")[1]);

            // If x=y diagonal line, then the difference of x coordinates = difference of y coordinates
            if (x1 - x2 == y1 - y2)
                if (x1 < x2)
                    for (int i = 0; i <= x2-x1; i++)
                        // Increase incrementally for each coordinate since x=y diagonal line
                        matrix[x1+i][y1+i]++;
                else
                    for (int i = 0; i <= x1-x2; i++)
                        matrix[x2+i][y2+i]++;
            // If x=-y or vice versa, then diff of x = -diff of y (or vice versa, again)
            else if (x1 - x2 == -(y1 - y2))
                if (x1 < x2)
                    for (int i = 0; i <= x2-x1; i++)
                        // In this case, the x coordinate will increase, but then the y coordinate will decrease
                        // This can also be done the other way around (x decreases, y increases)
                        matrix[x1+i][y1-i]++;
                else
                    for (int i = 0; i <= x1-x2; i++)
                        matrix[x2+i][y2-i]++;
            // The rest is the same as previous task
            else if (x1 == x2)
                if (y1 < y2)
                    for (int i = y1; i <= y2; i++)
                        matrix[x1][i]++;
                else
                    for (int i = y2; i <= y1; i++)
                        matrix[x1][i]++;
            else if (y1 == y2)
                if (x1 < x2)
                    for (int i = x1; i <= x2; i++)
                        matrix[i][y1]++;
                else
                    for (int i = x2; i <= x1; i++)
                        matrix[i][y1]++;
        }

        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                if (matrix[i][j] > 1)
                    count++;

        System.out.println(count);
    }
}
