package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day2Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/src/Day2/input_day2.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);

        int horiz = 0;
        int aim = 0;
        int depth = 0;

        while (sc.hasNext()) {
            String dir = sc.next();
            int val = sc.nextInt();
            if (dir.equals("forward")) {
                horiz += val;
                depth += (aim * val);
            }
            else if (dir.equals("down"))
                aim += val;
            else if (dir.equals("up"))
                aim -= val;
        }

        System.out.println(horiz * depth);
    }
}
