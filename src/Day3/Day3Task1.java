package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day3Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/src/Day3/input_day3.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);

        int[] ones = new int[12];
        int[] zeros = new int[12];

        while(sc.hasNext()) {
            String temp = sc.next();
            char[] temp_char = temp.toCharArray();
            for (int i = 0; i < 12; i++)
                if (temp_char[i] == '1')
                    ones[i]++;
                else zeros[i]++;
        }
        String gamma = "";
        String epsilon = "";

        for (int i = 0; i < 12; i++) {
            if (ones[i] > zeros[i]) {
                gamma += '1';
                epsilon += '0';
            } else {
                gamma += '0';
                epsilon += '1';
            }
        }

        System.out.println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));
    }
}
