package Day7;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day7Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(7);

        String inp = sc.next();
        String[] numbers = inp.split(",");
        ArrayList<Integer> list = new ArrayList<>();

        for (String number : numbers)
            list.add(Integer.parseInt(number));

        // Sort numbers so we can find median value faster
        Collections.sort(list);

        int res = 0;
        // We get the median value because it is not as affected as outliers. In this case, since distance is
        // linear from the position we need to find from each crab submarine, then we try to take the median
        // to avoid outliers having very much effect on it (we want the values closer to the center to have the smallest
        // fuel consumption since outliers are the fewest when we talk about a distribution, therefore we can sacrifice
        // them using more fuel in order to get a lower fuel consumption overall
        int median = list.get(list.size() / 2);

        // Add the differences between the submarine's position and the median position, which will give us the fuel
        // consumption for each submarine
        for (int a : list)
            res += Math.abs(a - median);

        System.out.println(res);
    }
}
