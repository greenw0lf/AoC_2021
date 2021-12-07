package Day7;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day7Task2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(7);

        String inp = sc.next();
        String[] numbers = inp.split(",");
        ArrayList<Integer> list = new ArrayList<>();

        for (String number : numbers)
            list.add(Integer.parseInt(number));

        Collections.sort(list);

        int res = 0;
        // In this case, we need to take into account outliers since distance from an outlier to a submarine increases
        // much more compared to the previous exercise. Therefore, the mean is the best measure in this case to find the
        // point where crabs would need to move to
        int mean = 0;

        for (int a : list)
            mean += a;

        // I would assume rounding would be a better idea since, for the example on the website, the mean would be
        // 4.8 which rounds up to 5. However, in this case, the mean value was very close to .5, and flooring
        // seems to give a better result.
        mean = (int) Math.floor((float) mean / list.size());

        for (int a : list) {
            // We apply Gaussian sum formula, with n = difference between submarine's position and mean
            int n = (Math.abs(a - mean));
            res += (n * (n + 1) / 2);
        }

        System.out.println(res);
    }
}
