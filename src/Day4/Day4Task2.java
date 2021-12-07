package Day4;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(4);

        // This will be the bingo numbers that we will call out to mark on our tables
        ArrayList<Integer> calls = new ArrayList<>();
        // This will contain the table with the numbers that we will use later on to compute result
        ArrayList<ArrayList<Integer>> lookupTables = new ArrayList<>();
        // This will mark the numbers up and determine when bingo is reached
        ArrayList<int[][]> markupTables = new ArrayList<>();

        // We get the entire list of calls since Java is being annoying and cannot separate them by ,
        String call = sc.next();

        // Separate the number calls and put them into an array of strings
        String[] callsTemp = call.split(",");
        // Convert from string to number
        for (String s : callsTemp) calls.add(Integer.parseInt(s));

        while (sc.hasNext()) {
            ArrayList<Integer> table = new ArrayList<>();
            // Keeping the lookup table flat since it is an array list to find the index easier later on
            for (int i = 0; i < 25; i++)
                table.add(sc.nextInt());
            // Add the newly created lookup table to the list of lookup tables
            lookupTables.add(table);
            // Have to make the markup table every time for each of the lookup tables because I cannot assign the same
            // matrix to all of them due to shallow copying, which then ruins all the tables when they should be separate
            int[][] zeros = new int[5][5];
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    zeros[i][j] = 0;
            // Finally, add the table of zeros to the list of markup tables
            markupTables.add(zeros);
        }

        // Index of the calls
        int ind = 0;
        int res = 0;
        // We keep going until there is 1 table left, which is the losing table we are looking for
        while (lookupTables.size() > 1) {
            // Go through all the lookup tables
            for (int i = 0; i < lookupTables.size(); i++) {
                // Find index of the number in the lookup table
                int lol = lookupTables.get(i).indexOf(calls.get(ind));
                // If number exists in the table
                if (lol > -1) {
                    // Converting from 1D lookup table to 2D markup table
                    int x = lol / 5;
                    int y = lol % 5;
                    // Mark the number
                    markupTables.get(i)[x][y] = 1;
                    // Check if bingo has been hit
                    for (int j = 0; j < 5; j++) {
                        int xcount = 0;
                        int ycount = 0;
                        for (int k = 0; k < 5; k++) {
                            // Check if row is all marked
                            if (markupTables.get(i)[j][k] == 1)
                                xcount++;
                            // Check if column is all marked
                            if (markupTables.get(i)[k][j] == 1)
                                ycount++;
                        }
                        // If one row/column has all 5 elements marked
                        if (xcount == 5 || ycount == 5) {
                            // We remove the table since we are not interested in it anymore
                            lookupTables.remove(i);
                            markupTables.remove(i);
                            // Decrement index in order to not skip a table
                            i--;
                            break;
                        }
                    }
                }
            }
            // Increase the index to the next number call, in case no bingo has been found yet
            ind++;
        }

        // Work on the last bingo table until we hit bingo
        while (res == 0) {
            int lol = lookupTables.get(0).indexOf(calls.get(ind));
            if (lol > -1) {
                // Converting from 1D lookup table to 2D markup table
                int x = lol / 5;
                int y = lol % 5;
                // Mark the number
                markupTables.get(0)[x][y] = 1;
                // Check if bingo has been hit
                for (int j = 0; j < 5; j++) {
                    int xcount = 0;
                    int ycount = 0;
                    for (int k = 0; k < 5; k++) {
                        // Check if row is all marked
                        if (markupTables.get(0)[j][k] == 1)
                            xcount++;
                        // Check if column is all marked
                        if (markupTables.get(0)[k][j] == 1)
                            ycount++;
                    }
                    // If one row/column has all 5 elements marked
                    if (xcount == 5 || ycount == 5) {
                        // Go through markup table to find unmarked elements
                        for (int m = 0; m < 5; m++)
                            for (int n = 0; n < 5; n++)
                                // If element is unmarked
                                if (markupTables.get(0)[m][n] == 0)
                                    // Add it to the result
                                    res += lookupTables.get(0).get(5 * m + n);
                        // Finally, multiply the called-out number with the sum of unmarked numbers
                        res *= calls.get(ind);
                        break;
                    }
                }
            }
            // Increase the index to the next number call, in case no bingo has been found yet
            ind++;
        }
        System.out.println(res);
    }
}
