package Day10;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Day10Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(10);

        // Keep track of each kind of illegal character and its number of appearances
        int[] buckets = new int[4];
        ArrayList<String> lines = new ArrayList<>();

        while (sc.hasNext())
            lines.add(sc.next());
        for (String s: lines) {
            Stack<Character> stack = new Stack<>();
            char[] inp = s.toCharArray();

            for (char c: inp)
                // If it's an opening bracket, we add it to the top of the stack
                if (c == '(' || c == '[' || c == '{' || c == '<')
                    stack.add(c);
                // If ( does not match ) on top of stack, then illegal character found and we stop
                else if (c == ')' && stack.peek() != '(') {
                    buckets[0]++;
                    break;
                }
                // If [ does not match ] on top of stack, then illegal character found and we stop
                else if (c == ']' && stack.peek() != '[') {
                    buckets[1]++;
                    break;
                }
                // If { does not match } on top of stack, then illegal character found and we stop
                else if (c == '}' && stack.peek() != '{') {
                    buckets[2]++;
                    break;
                }
                // If < does not match > on top of stack, then illegal character found and we stop
                else if (c == '>' && stack.peek() != '<') {
                    buckets[3]++;
                    break;
                }
                // Otherwise, character matches top of stack, therefore we remove the bracket from the stack since its
                // match was already found.
                else {
                    stack.pop();
                }
        }

        System.out.println(buckets[0] * 3 + buckets[1] * 57 + buckets[2] * 1197 + buckets[3] * 25137);
    }
}
