package Day3;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(3);

        // We will count the amount of zero and one bits on each position with these variables
        int ones = 0;
        int zeros = 0;

        // 2 different arrays since the values contained in each of them will be different, and we do not
        // want to deal with both at the same time as the result will be affected by that.
        ArrayList<String> o2 = new ArrayList<>();
        ArrayList<String> co2 = new ArrayList<>();

        while (sc.hasNext()) {
            String temp = sc.next();
            // Add number to both to then filter out arrays separately, as mentioned above
            o2.add(temp);
            co2.add(temp);
        }

        for (int i = 0; i < 12 && (o2.size() > 1 || co2.size() > 1); i++) {
            for (int j = 0; j < o2.size(); j++) {
                // Counting like in the previous task
                char[] temp = o2.get(j).toCharArray();
                if (temp[i] == '1')
                    ones++;
                else zeros++;
            }
            // Made a variable to use it in the lambda function below
            int finalI = i;
            // We remove the least significant bit which is 0 (only if array is not already at 1 element, otherwise
            // we found our o2 rating)
            if (ones >= zeros && o2.size() > 1)
                o2.removeIf((String yooo) -> yooo.toCharArray()[finalI] == '0');
            // Otherwise (again, if more than one element), we remove 1 because least significant bit
            else if (o2.size() > 1) o2.removeIf((String yooo) -> yooo.toCharArray()[finalI] == '1');
            // Reset count for co2 rating search since different numbers might be in the array compared to o2
            ones = 0;
            zeros = 0;
            for (int j = 0; j < co2.size(); j++) {
                char[] temp = co2.get(j).toCharArray();
                if (temp[i] == '1')
                    ones++;
                else zeros++;
            }
            // We remove the most significant bit which is 1 (only if array size > 1, otherwise we have found our
            // co2 rating)
            if (ones >= zeros && co2.size() > 1)
                co2.removeIf((String aaa) -> aaa.toCharArray()[finalI] == '1');
            // Remove 0 if most significant bit (only if size is above 1)
            else if (co2.size() > 1) co2.removeIf((String aaa) -> aaa.toCharArray()[finalI] == '0');
            // Reset for next position of bits
            ones = 0;
            zeros = 0;
        }

        // The arrays will have 1 element only in the end, therefore it will be the element in the first position
        String o2Final = o2.get(0);
        String co2Final = co2.get(0);

        System.out.println(Integer.parseInt(o2Final, 2) * Integer.parseInt(co2Final, 2));
    }
}
