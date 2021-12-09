package Day1;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(1);
        int depth1 = -1;
        int depth2 = -1;
        int res = 0;
        while (sc.hasNext()) {
            int temp = sc.nextInt();
            // Initial depth value declaration
            if (depth1 == -1)
                depth1 = temp;
            // If first depth already exists, we go onto the 2nd one
            else if (depth2 == -1) {
                depth2 = temp;
                // If depth increased, we increment the result
                if (depth2 - depth1 > 0)
                    res++;
            }
            // If depths are already defined
            else {
                // The depth we were at becomes the previous one
                depth1 = depth2;
                // Get the depth we are at now
                depth2 = temp;
                // If depth increased, we increment the result
                if (depth2 - depth1 > 0)
                    res++;
            }
        }
        System.out.println(res);
    }
}
