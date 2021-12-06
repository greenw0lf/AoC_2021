package Day2;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(2);

        // 2 variables for each direction
        int horiz = 0;
        int depth = 0;

        while (sc.hasNext()) {
            String dir = sc.next();
            int val = sc.nextInt();
            // If we go forward, horizontal direction increases by value of forward
            if (dir.equals("forward"))
                horiz += val;
            // If we go down, vertical direction increases by value of down
            else if (dir.equals("down"))
                depth += val;
            // If we go up, vertical direction decreases by value of up
            else if (dir.equals("up"))
                depth -= val;
        }

        System.out.println(horiz * depth);
    }
}
