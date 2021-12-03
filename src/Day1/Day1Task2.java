package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/src/Day1/input_day1.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);

        ArrayList<Integer> input = new ArrayList<>();
        int res = 0;

        while (sc.hasNext())
            input.add(sc.nextInt());
        for (int i = 3; i < input.size(); i++) {
            if (input.get(i) + input.get(i-1) + input.get(i-2) >
                    input.get(i-1) + input.get(i-2) + input.get(i-3))
                res++;
        }

        System.out.println(res);
    }
}
