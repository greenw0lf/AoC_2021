package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day6Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/AoC_2021/src/Day6/input_day6.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);
    }
}
