package Day9;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
    private final int i;
    private final int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }
}

public class Day9Part2 {
    public static int basinSize(int[][] inp, int i_st, int j_st) {
        // BFS works best for this problem, I found.
        int size = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i_st,j_st));
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int i = p.getI();
            int j = p.getJ();
            if (inp[i][j] == 9)
                continue;
            // We only increase the size of the current element if it isn't a 9
            size++;
            // We check against all neighbors of the current position, unless they have no neighbor to the
            // 1. left
            // 2. above
            // 3. right
            // 4. below
            if (i - 1 >= 0)
                // We check if the element is 9 because we do not want to add it to the queue
                if (inp[i - 1][j] > inp[i][j] && inp[i - 1][j] != 9) {
                    q.add(new Pair(i - 1, j));
                }
            if (j - 1 >= 0)
                if (inp[i][j - 1] > inp[i][j] && inp[i][j - 1] != 9) {
                    q.add(new Pair(i, j - 1));
                }
            if (i + 1 < 100)
                if (inp[i + 1][j] > inp[i][j] && inp[i + 1][j] != 9) {
                    q.add(new Pair(i + 1, j));
                }
            if (j + 1 < 100)
                if (inp[i][j + 1] > inp[i][j] && inp[i][j + 1] != 9) {
                    q.add(new Pair(i, j + 1));
                }
            // Make the number we checked 9 in order to not check it again. Since we work on the actual input matrix,
            // the changes will be reflected in the queue as well since we check the actual table when we get
            // the indices from the queue.
            inp[i][j] = 9;
        }

        return size;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(9).useDelimiter("");

        int[][] map = new int[100][100];
        int[] largest = new int[3];

        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 100; j++)
                map[i][j] = sc.nextInt();
            // For some reason, new lines are made of 2 characters?
            sc.next();
            sc.next();
        }

        // For the last line, there is no new line character(s)
        for (int i = 0; i < 100; i++)
            map[99][i] = sc.nextInt();

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++) {
                // We remember the index of the smallest value in the neighborhood
                int minI = i+j;
                // We check the neighbors
                if (i-1 >= 0)
                    if (map[i-1][j] <= map[i][j])
                        minI = i+j-1;
                if (j-1 >= 0)
                    if (map[i][j-1] <= map[i][j])
                        minI = i+j-1;
                if (i+1 < 100)
                    if (map[i+1][j] <= map[i][j])
                        minI = i+j+1;
                if (j+1 < 100)
                    if (map[i][j+1] <= map[i][j])
                        minI = i+j+1;
                // If index has not changed (it can be either i+j, 1 higher, or 1 lower), then we find the basin size
                if (minI == i+j) {
                    int basin = basinSize(map, i, j);
                    for (int k = 0; k < 3; k++)
                        // If what we found is larger than the current one
                        if (basin > largest[k]) {
                            // We switch the former largest one with the basin to replace the 2nd largest with the
                            // former largest.
                            int temp = basin;
                            basin = largest[k];
                            largest[k] = temp;
                        }
                }
            }

        System.out.println(largest[0] * largest[1] * largest[2]);
    }
}
