package Day1;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(1);

        ArrayList<Integer> input = new ArrayList<>();
        int res = 0;

        // Store input in an arraylist for easier access
        while (sc.hasNext())
            input.add(sc.nextInt());
        for (int i = 3; i < input.size(); i++) {
            // i = current time
            // If val(i)+val(i-1)+val(i-2) > val(i-1)+val(i-2)+val(i-3), then increment result
            if (input.get(i) + input.get(i-1) + input.get(i-2) >
                    input.get(i-1) + input.get(i-2) + input.get(i-3))
                res++;
        }

        System.out.println(res);
    }
}
