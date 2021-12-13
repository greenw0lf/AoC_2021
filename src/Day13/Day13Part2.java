package Day13;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13Part2 {
    // Used to print the final result, to make it more legible
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(13).useDelimiter(",|\\r\\n");
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;

        while (sc.hasNextInt()) {
            x.add(sc.nextInt());
            y.add(sc.nextInt());

            if (x.get(x.size() - 1) > maxX)
                maxX = x.get(x.size() - 1);
            if (y.get(y.size() - 1) > maxY)
                maxY = y.get(y.size() - 1);
        }

        maxX++;
        maxY++;

        int[][] paper = new int[maxX][maxY];
        for (int i = 0; i < x.size(); i++)
            paper[x.get(i)][y.get(i)] = 1;

        // Read the folds
        ArrayList<String> folds = new ArrayList<>();
        while (sc.hasNext()) {
            // We only care about the "x=???" or "y=???" parts
            folds.add(sc.next().split(" ")[2]);
        }

        int[][] folded;

        // Fold one by one
        for (String fold: folds) {
            if (fold.toCharArray()[0] == 'x') {
                int mid = Integer.parseInt(fold.substring(2));
                folded = new int[mid][maxY];
                for (int i = 0; i < mid; i++)
                    for (int j = 0; j < maxY; j++)
                        folded[i][j] = Math.max(paper[i][j], paper[mid + (mid - i)][j]);
                // Make the old paper be the new, folded one
                paper = new int[mid][maxY];
                for (int i = 0; i < mid; i++)
                    for (int j = 0; j < maxY; j++)
                        paper[i][j] = folded[i][j];
                maxX = mid;
            }

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
        }

        // Print the final folded paper in a nice-looking format
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++)
                if (paper[j][i] == 1)
                    System.out.print(ANSI_RED + '▓' + ANSI_RESET);
                else
                    System.out.print(ANSI_GREEN + '▓' + ANSI_RESET);
            System.out.println();
        }
    }
}
