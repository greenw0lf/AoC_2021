package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day1Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/AoC_2021/src/Day1/input_day1.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);
        int depth1 = -1;
        int depth2 = -1;
        int res = 0;
        while (sc.hasNext()) {
            int temp = sc.nextInt();
            if (depth1 == -1)
                depth1 = temp;
            else if (depth2 == -1) {
                depth2 = temp;
                if (depth2 - depth1 > 0)
                    res++;
            }
            else {
                depth1 = depth2;
                depth2 = temp;
                if (depth2 - depth1 > 0)
                    res++;
            }
        }
        System.out.println(res);
    }
}
