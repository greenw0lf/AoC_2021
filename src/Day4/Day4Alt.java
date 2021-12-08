package Day4;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4Alt {
    public static boolean task1(ArrayList<int[][]> tables, int index, int call) {
        int res = 0;
        // Go through markup table to find unmarked elements
        for (int m = 0; m < 5; m++)
            for (int n = 0; n < 5; n++)
                // If element is unmarked
                if (tables.get(index)[m][n] != -1)
                    // Add it to the result
                    res += tables.get(index)[m][n];
        // Finally, multiply the called-out number with the sum of unmarked numbers
        res *= call;

        System.out.println(res);
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean task1done = false;
        Scanner sc = ReadingOfFiles.readFile(4);

        // This will be the bingo numbers that we will call out to mark on our tables
        ArrayList<Integer> calls = new ArrayList<>();
        // This will contain the bingo tables
        ArrayList<int[][]> tables = new ArrayList<>();

        // We get the entire list of calls since Java is being annoying and cannot separate them by ,
        String call = sc.next();

        // Separate the number calls and put them into an array of strings
        String[] callsTemp = call.split(",");
        // Convert from string to number
        for (String s : callsTemp) calls.add(Integer.parseInt(s));

        while (sc.hasNext()) {
            int[][] table = new int[5][5];
            // Keeping the lookup table flat since it is an array list to find the index easier later on
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    table[i][j] = sc.nextInt();
            // Add the newly created lookup table to the list of tables
            tables.add(table);
        }

        // Index of the calls
        int ind = 0;
        int res = 0;
        // We keep going until there is 1 table left, which is the losing table we are looking for
        while (tables.size() > 1) {
            // Go through all the tables
            for (int i = 0; i < tables.size(); i++) {
                // Find called number in the table
                for (int k = 0; k < 5; k++)
                    for (int l = 0; l < 5; l++)
                        // If we found the number, we replace it with -1 to mark it
                        if (tables.get(i)[k][l] == calls.get(ind))
                            tables.get(i)[k][l] = -1;
                // Check if bingo has been hit
                for (int j = 0; j < 5; j++) {
                    int xcount = 0;
                    int ycount = 0;
                    for (int k = 0; k < 5; k++) {
                        // Check if row is all marked
                        if (tables.get(i)[j][k] == -1)
                            xcount++;
                        // Check if column is all marked
                        if (tables.get(i)[k][j] == -1)
                            ycount++;
                    }
                    // If one row/column has all 5 elements marked
                    if ((xcount == 5 || ycount == 5)) {
                        if (!task1done)
                            task1done = task1(tables, i, calls.get(ind));
                        // We remove the table since we are not interested in it anymore
                        tables.remove(i);
                        // Decrement index in order to not skip a table
                        i--;
                        break;
                    }
                }
            }
            // Increase the index to the next number call, in case no bingo has been found yet
            ind++;
        }

        // Work on the last bingo table until we hit bingo
        while (res == 0) {
            // Find called number in the table
            for (int k = 0; k < 5; k++)
                for (int l = 0; l < 5; l++)
                    // If we found the number, we replace it with -1 to mark it
                    if (tables.get(0)[k][l] == calls.get(ind))
                        tables.get(0)[k][l] = -1;
            // Check if bingo has been hit
            for (int j = 0; j < 5; j++) {
                int xcount = 0;
                int ycount = 0;
                for (int k = 0; k < 5; k++) {
                    // Check if row is all marked
                    if (tables.get(0)[j][k] == -1)
                        xcount++;
                    // Check if column is all marked
                    if (tables.get(0)[k][j] == -1)
                        ycount++;
                }
                // If one row/column has all 5 elements marked
                if (xcount == 5 || ycount == 5) {
                    // Go through markup table to find unmarked elements
                    for (int m = 0; m < 5; m++)
                        for (int n = 0; n < 5; n++)
                            // If element is unmarked
                            if (tables.get(0)[m][n] != -1)
                                // Add it to the result
                                res += tables.get(0)[m][n];
                    // Finally, multiply the called-out number with the sum of unmarked numbers
                    res *= calls.get(ind);
                    break;
                }
            }
            // Increase the index to the next number call, in case no bingo has been found yet
            ind++;
        }
        System.out.println(res);
    }
}
