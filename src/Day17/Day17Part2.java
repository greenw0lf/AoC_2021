package Day17;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day17Part2 {
    public static int landInArea(int vx, int vy, int[] ranges) {
        int x = 0;
        int y = 0;

        // While x is smaller than the upper range since afterwards it will definitely be out of range, no matter the y,
        // or if y is still bigger than the lower y range since y keeps going down, and if it goes beyond the lowest
        // point of the range, then it will not matter if it eventually arrives with the x coordinate in the range
        // because it it already out of range for y.
        while (x < ranges[1] && y > ranges[2]) {
            // We move the satellite one more step with the current velocities
            x += vx;
            y += vy;

            // If we reach the target area, then we count it and stop
            if (ranges[0] <= x && x <= ranges[1] && ranges[2] <= y && y <= ranges[3])
                return 1;

            // Decrease the velocities according to the problem's rules
            vx = vx != 0 ? vx - 1 : vx;
            vy--;
        }

        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(17);

        sc.next();
        sc.next();

        String x = sc.next();
        String y = sc.next();

        // we stop at the length - 1 because of the comma being parsed into the x=....., string
        x = x.substring(2, x.length() - 1);
        y = y.substring(2);

        String[] xRange = x.split("\\.\\.");
        String[] yRange = y.split("\\.\\.");

        int xMin = Integer.parseInt(xRange[0]);
        int xMax = Integer.parseInt(xRange[1]);
        int yMin = Integer.parseInt(yRange[0]);
        int yMax = Integer.parseInt(yRange[1]);

        // Save an array of ranges
        int[] ranges = {xMin, xMax, yMin, yMax};

        int xSearch = 0;
        // We use the formula discovered in the previous part since it is the maximum altitude it can climb to
        int ySearch = -yMin-1;

        // All the code below is heavily tailored to the input, if the y's were positive or the x's negative, it would
        // probably not work as well.

        // We start with the smallest n for which its Gaussian sum would be in the range of x, as it is the smallest
        // velocity that can still reach the range.
        while (xSearch * (xSearch + 1) / 2 < xMin)
            xSearch++;

        // The number of initial velocities that get us in the grid area
        int count = 0;

        for (int i = xSearch; i <= xMax; i++)
            for (int j = yMin; j <= ySearch; j++)
                count += landInArea(i, j, ranges);

        System.out.println(count);
    }
}
