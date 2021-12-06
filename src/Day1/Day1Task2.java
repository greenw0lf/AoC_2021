package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/AoC_2021/src/Day1/input_day1.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);

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
