package Read;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadingOfFiles {
    public static Scanner readFile(Integer day) throws FileNotFoundException {
        String pathname = "D://AoC/AoC_2021/src/Day" + day.toString() + "/input_day" + day.toString() + ".txt";
        Path path = Path.of(pathname);
        File file = path.toFile();
        return new Scanner(file);
    }
}
