package Day13;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(13).useDelimiter(",|\\r\\n");
        // Save the points' x and y coordinates where there are dots, in the right order
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        // Used to determine size of paper
        int maxX = 0;
        int maxY = 0;

        // Read the points and save the maximum X and Y
        while (sc.hasNextInt()) {
            x.add(sc.nextInt());
            y.add(sc.nextInt());

            if (x.get(x.size() - 1) > maxX)
                maxX = x.get(x.size() - 1);
            if (y.get(y.size() - 1) > maxY)
                maxY = y.get(y.size() - 1);
        }

        // Increase them to use them as size numbers for the paper (matrix)
        maxX++;
        maxY++;

        // Assign 1 wherever we have a point
        int[][] paper = new int[maxX][maxY];
        for (int i = 0; i < x.size(); i++)
            paper[x.get(i)][y.get(i)] = 1;

        // Read the first folding
        String fold = sc.next().split(" ")[2];

        // The paper after it is folded once
        int[][] folded;

        // The number of points the folded paper will have
        int res = 0;

        // folding along x
        if (fold.toCharArray()[0] == 'x') {
            // get the number of the "fold along x=???" string
            int mid = Integer.parseInt(fold.substring(2));
            // Define the folded paper by the x to fold along and maintain the same max Y
            folded = new int[mid][maxY];
            for (int i = 0; i < mid; i++)
                for (int j = 0; j < maxY; j++)
                    // We add the maximum between one side of the fold and the other since we just want overlapping
                    // points
                    folded[i][j] = Math.max(paper[i][j], paper[mid + (mid - i)][j]);
            // This will be used in part 2 mostly
            paper = new int[mid][maxY];
            for (int i = 0; i < mid; i++)
                for (int j = 0; j < maxY; j++)
                    paper[i][j] = folded[i][j];
            // Set the new x maximum to the fold value
            maxX = mid;
        }

        // folding along y
        // Similar description to x, except it's applied to y
        if (fold.toCharArray()[0] == 'y') {
            int mid = Integer.parseInt(fold.substring(2));
            folded = new int[maxX][mid];
            for (int i = 0; i < maxX; i++)
                for (int j = 0; j < mid; j++)
                    folded[i][j] += Math.max(paper[i][j], paper[i][mid + (mid - j)]);
            paper = new int[maxX][mid];
            for (int i = 0; i < maxX; i++)
                for (int j = 0; j < mid; j++)
                    paper[i][j] = folded[i][j];
            maxY = mid;
        }

        // Count the number of points on the folded paper
        for (int i = 0; i < maxX; i++)
            for (int j = 0; j < maxY; j++)
                res += paper[i][j];

        System.out.println(res);
    }
}
