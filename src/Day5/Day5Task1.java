package Day5;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5Task1 {

    public static void main(String []args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(5);
        int[][] matrix = new int[1000][1000];

        // Making the matrix of lines
        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                matrix[i][j] = 0;

        int count = 0;

        while (sc.hasNext()) {
            // Java is annoying, therefore I have to read the 2 coordinates and split them afterwards
            String x1y1 = sc.next();
            int x1 = Integer.parseInt(x1y1.split(",")[0]);
            int y1 = Integer.parseInt(x1y1.split(",")[1]);
            // The arrow that is not important aside from separating point 1 from point 2
            String ignore = sc.next();
            String x2y2 = sc.next();
            int x2 = Integer.parseInt(x2y2.split(",")[0]);
            int y2 = Integer.parseInt(x2y2.split(",")[1]);

            // If the x coordinate is the same (horizontal line)
            if (x1 == x2)
                // If y1 < y2, then we run a loop from y1 to y2
                if (y1 < y2)
                    for (int i = y1; i <= y2; i++)
                        matrix[x1][i]++;
                // Otherwise, run loop from y2 to y1
                else
                    for (int i = y2; i <= y1; i++)
                        matrix[x1][i]++;
            // If the y coordinate is the same (vertical line)
            else if (y1 == y2)
                if (x1 < x2)
                    for (int i = x1; i <= x2; i++)
                        matrix[i][y1]++;
                else
                    for (int i = x2; i <= x1; i++)
                        matrix[i][y1]++;
        }

        // Count all points where 2 or more lines intersect (count >= 2)
        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                if (matrix[i][j] > 1)
                    count++;

        System.out.println(count);
    }
}