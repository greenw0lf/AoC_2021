package Day17;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day17Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(17);

        // Ignore the text at the beginning
        sc.next();
        sc.next();

        // Get the x=..... text
        String x = sc.next();
        // Get the y=..... text
        String y = sc.next();

        // We only care about y for now, so we ignore the "y=" part here
        y = y.substring(2);

        // Separate the numbers
        String[] yRange = y.split("\\.\\.");

        // Get only the minimum y, which is the first of the range defined
        int yMin = Integer.parseInt(yRange[0]);

        // We start with a Gaussian sum from 0 to -yMin-1, since next step, if it were to fall down, would be yMin,
        // which would be barely at the edge. This should guarantee the highest point we can reach.
        int n = -yMin - 1;

        // The result should be that y=-yMin-1, which is Gaussian sum of 124.
        System.out.println(n * (n+1) / 2);
    }
}
