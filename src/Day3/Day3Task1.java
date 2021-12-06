package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day3Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/AoC_2021/src/Day3/input_day3.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);

        // counters for ones and zeros for each bit position (input has 12 bits)
        int[] ones = new int[12];
        int[] zeros = new int[12];

        while(sc.hasNext()) {
            String temp = sc.next();
            // Make it into char array for easier access of each bit position
            char[] temp_char = temp.toCharArray();
            for (int i = 0; i < 12; i++)
                // Increase the corresponding position in the ones array if bit is 1
                if (temp_char[i] == '1')
                    ones[i]++;
                // Otherwise, increase the corresponding bit position in the zeros array
                else zeros[i]++;
        }
        String gamma = "";
        String epsilon = "";

        for (int i = 0; i < 12; i++) {
            // If 1 is most significant, then we add it to gamma and we add 0 to epsilon
            if (ones[i] > zeros[i]) {
                gamma += '1';
                epsilon += '0';
            // Otherwise, we add 0 to gamma and 1 to epsilon
            } else {
                gamma += '0';
                epsilon += '1';
            }
        }

        // Convert back into int to then multiply and get our result
        System.out.println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));
    }
}
