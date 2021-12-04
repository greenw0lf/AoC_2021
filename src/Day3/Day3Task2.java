package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("D://AoC/AoC_2021/src/Day3/input_day3.txt");
        File file = path.toFile();
        Scanner sc = new Scanner(file);

        int ones = 0;
        int zeros = 0;

        ArrayList<String> o2 = new ArrayList<>();
        ArrayList<String> co2 = new ArrayList<>();

        while (sc.hasNext()) {
            String temp = sc.next();
            o2.add(temp);
            co2.add(temp);
        }

        for (int i = 0; i < 12 && (o2.size() > 1 || co2.size() > 1); i++) {
            for (int j = 0; j < o2.size(); j++) {
                char[] temp = o2.get(j).toCharArray();
                if (temp[i] == '1')
                    ones++;
                else zeros++;
            }
            int finalI = i;
            if (ones >= zeros && o2.size() > 1)
                o2.removeIf((String yooo) -> yooo.toCharArray()[finalI] == '0');
            else if (o2.size() > 1) o2.removeIf((String yooo) -> yooo.toCharArray()[finalI] == '1');
            ones = 0;
            zeros = 0;
            for (int j = 0; j < co2.size(); j++) {
                char[] temp = co2.get(j).toCharArray();
                if (temp[i] == '1')
                    ones++;
                else zeros++;
            }
            if (ones >= zeros && co2.size() > 1)
                co2.removeIf((String aaa) -> aaa.toCharArray()[finalI] == '1');
            else if (co2.size() > 1) co2.removeIf((String aaa) -> aaa.toCharArray()[finalI] == '0');
            ones = 0;
            zeros = 0;
        }

        String o2_final = o2.get(0);
        String co2_final = co2.get(0);

        System.out.println(Integer.parseInt(o2_final, 2) * Integer.parseInt(co2_final, 2));
    }
}
