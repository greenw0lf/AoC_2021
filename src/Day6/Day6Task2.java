package Day6;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(6);

        String allNum = sc.next();
        String[] numbers = allNum.split(",");
        // Bucket array for each day of a lantern fish
        long[] lanternfish = new long[9];

        for (String temp : numbers)
            // Add the current number of days to the corresponding bucket
            lanternfish[Integer.parseInt(temp)]++;

        // The cycle of 256 days
        for (int t = 0; t < 256; t++) {
            System.out.println(Arrays.toString(lanternfish));
            // The implementation works as follows (example):
            // 1. Save the number of lanternfish in previous day (5 days left until generation of new fish) into prev
            // 2. Also save the number of lanternfish in current day (6 days left until generation of new fish) into next
            // 3. Overwrite the number on 5 days with the current number on 6 days (next)
            // 4. Put prev into next
            // 5. Repeat the loop until all fish have been moved to the previous day (decrement day by 1)
            // 6. At the end, overwrite the number of lanternfish with 8 days until generation (since they should already
            // be decremented) with the number of fish who generate other new fish (0 days), which is contained within
            // either prev or next (either works)
            // 7. Increase the current number of fish with 6 days left with the number of fish who generate now
            long prev;
            long next = lanternfish[8];
            for (int i = 8; i > 0; i--) {
                prev = lanternfish[i-1];
                lanternfish[i - 1] = next;
                next = prev;
            }
            lanternfish[8] = next;
            lanternfish[6] += next;
        }

        System.out.println(Arrays.toString(lanternfish));

        // Sum all the fish with different days left until generating new fish
        long sum = 0;
        for (int i = 0; i < 9; i++)
            sum += lanternfish[i];

        System.out.println(sum);
    }
}
