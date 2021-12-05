package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day4Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/AoC_2021/src/Day4/input_day4.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);
    }
}
