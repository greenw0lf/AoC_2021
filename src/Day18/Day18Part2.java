package Day18;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day18Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(18);
        String[] input = new String[100];
        for (int i = 0; i < 100; i++)
            input[i] = sc.next();

        int maxMag = -1;

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++) {
                if (i == j)
                    continue;
                Snailfish l = new Snailfish(-1);
                Snailfish r = new Snailfish(-1);
                l.parseString(input[i]);
                r.parseString(input[j]);
                Snailfish root = new Snailfish(-1);
                root.left = l;
                root.right = r;
                root.left.parent = root;
                root.right.parent = root;

                int spl = -1;
                while (spl == -1 || root.height() > 4) {
                    spl = -1;
                    if (root.height() > 4) {
                        root.explode();
                    } else {
                        spl = root.split();
                    }
                }

                if (root.magnitude() > maxMag)
                    maxMag = root.magnitude();
            }
        System.out.println(maxMag);
    }
}
