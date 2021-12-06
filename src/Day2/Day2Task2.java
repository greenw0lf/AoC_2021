package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day2Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/AoC_2021/src/Day2/input_day2.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);

        // This will increase whenever the submarine moves forward
        int horiz = 0;
        // This will increase/decrease every time depth changes (down/up)
        int aim = 0;
        // This will increase/decrease whenever we move forward, based on value of aim at the time we move forward
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
