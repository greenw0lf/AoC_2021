package Day10;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Day10Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(10);

        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Long> scores = new ArrayList<>();

        while (sc.hasNext())
            lines.add(sc.next());
        for (String s: lines) {
            Stack<Character> stack = new Stack<>();
            // Keep track if for loop below was broken or not
            boolean incomplete = true;
            char[] inp = s.toCharArray();

            for (char c: inp)
                // If it's an opening bracket, we add it to the top of the stack
                if (c == '(' || c == '[' || c == '{' || c == '<')
                    stack.add(c);
                    // If ( does not match ) on top of stack, then illegal character found and we stop
                else if (c == ')' && stack.peek() != '(') {
                    // Incorrect input line, therefore mark as false
                    incomplete = false;
                    break;
                }
                // If [ does not match ] on top of stack, then illegal character found and we stop
                else if (c == ']' && stack.peek() != '[') {
                    incomplete = false;
                    break;
                }
                // If { does not match } on top of stack, then illegal character found and we stop
                else if (c == '}' && stack.peek() != '{') {
                    incomplete = false;
                    break;
                }
                // If < does not match > on top of stack, then illegal character found and we stop
                else if (c == '>' && stack.peek() != '<') {
                    incomplete = false;
                    break;
                }
                // Otherwise, character matches top of stack, therefore we remove the bracket from the stack since its
                // match was already found.
                else {
                    stack.pop();
                }

            // If it is incomplete, we start computing the score of the missing closing brackets.
            if (incomplete) {
                long score = 0;
                while (!stack.empty()) {
                    if (stack.peek() == '(')
                        score = (score * 5) + 1;
                    else if (stack.peek() == '[')
                        score = (score * 5) + 2;
                    else if (stack.peek() == '{')
                        score = (score * 5) + 3;
                    else
                        score = (score * 5) + 4;
                    stack.pop();
                }
                scores.add(score);
            }
        }

        // Sort array to get median value (middle value).
        Collections.sort(scores);
        // Index of median is size/2, and since size is always odd, it will always be floored and not ceiled.
        System.out.println(scores.get(scores.size() / 2));
    }
}
