package Day8;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day8Part2 {
    // Copied first 2 methods here from:
    // https://stackoverflow.com/questions/27668213/check-if-a-string-has-all-the-characters-of-another-string
    public static HashSet<Character> stringToCharacterSet(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public static boolean containsAllChars (HashSet<Character> container, HashSet<Character> contained) {
        return container.containsAll(contained);
    }

    // Checks if 2 strings have the same characters in them.
    public static boolean sameChars (HashSet<Character> inp1, HashSet<Character> inp2) {
        return containsAllChars(inp1, inp2) && containsAllChars(inp2, inp1);
    }

    public static void decider(ArrayList<String> inp, HashSet<Character>[] digits, int decide) {
        switch(decide) {
            // Easiest case where we can find 9 because it is the only one that contains same lines as 4 and extra
            case 0:
                for (int i = 0; i < inp.size(); i++)
                    if (containsAllChars(stringToCharacterSet(inp.get(i)), digits[4])) {
                        digits[9] = stringToCharacterSet(inp.remove(i));
                        break;
                    }
                break;
            // Only 3 and 0 share same lines as 1
            case 1:
                for (int i = 0; i < inp.size(); i++)
                    if (containsAllChars(stringToCharacterSet(inp.get(i)), digits[7])) {
                        // If it's 3, which has 5 lines
                        if (inp.get(i).length() == 5)
                            digits[3] = stringToCharacterSet(inp.remove(i));
                        // Else, 0 has 6 lines
                        else digits[0] = stringToCharacterSet(inp.remove(i));
                        i--;
                    }
                break;
            // Final case, where 5 and 6 share same lines and 2 is apart from them
            default:
                String six = "";
                for (int i = 0; i < inp.size(); i++)
                    // 6 is the only one with 6 lines from the 3 numbers
                    if (inp.get(i).length() == 6) {
                        six = inp.remove(i);
                        break;
                    }
                digits[6] = stringToCharacterSet(six);
                // If first one is 5
                if (containsAllChars(digits[6], stringToCharacterSet(inp.get(0)))) {
                    // 6 contains all of 5's lines
                    digits[5] = stringToCharacterSet(inp.get(0));
                    // 2 has a line that is not in 6
                    digits[2] = stringToCharacterSet(inp.get(1));
                    // Make sure to clear array, otherwise these values will carry on (so much time spent here...)
                    inp.clear();
                }
                // Else, second element remaining is 5 and first one is 2
                else {
                    digits[5] = stringToCharacterSet(inp.get(1));
                    digits[2] = stringToCharacterSet(inp.get(0));
                    inp.clear();
                }
                break;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(8);
        // This will be our input every time we read.
        String temp;
        int res = 0;

        // This will contain the input we are unsure of, that will need to be processed differently than looking
        // at the length of the string.
        ArrayList<String> input = new ArrayList<>();
        // This will have the actual digits in their corresponding place.
        HashSet<Character>[] digits = new HashSet[10];

        while (sc.hasNext()) {
            // Read the digit panels
            for (int i = 0; i < 10; i++) {
                temp = sc.next();
                switch(temp.length()) {
                    // Only 1 has 2 lines in his digit thing
                    case 2:
                        digits[1] = stringToCharacterSet(temp);
                        break;
                    // 7 has 1 more line than 1
                    case 3:
                        digits[7] = stringToCharacterSet(temp);
                        break;
                    // 4 has exactly 4 lines, nice
                    case 4:
                        digits[4] = stringToCharacterSet(temp);
                        break;
                    // 8 has the most lines available, 7
                    case 7:
                        digits[8] = stringToCharacterSet(temp);
                        break;
                    // Otherwise, uncertain case, therefore we put it in the to-be-processed list
                    default:
                        input.add(temp);
                }
            }

            System.out.println(input.toString());
            // 3 different cases to cover for uncertain numbers, which are explained in the decider method
            for (int i = 0; i < 3; i++)
                decider(input, digits, i);

            // Read the |
            temp = sc.next();
            String number = "";
            // The output we are interested to analyze.
            for (int k = 0; k < 4; k++) {
                temp = sc.next();
                // Go through the digits list
                for (int i = 0; i < 10; i++)
                    // If strings match, then we add the number (not the string!) to the 4-digit number of this line
                    if (sameChars(digits[i], stringToCharacterSet(temp)))
                        number += i;
            }
            // After reading the 4 inputs, we have a number, which we add to the final result
            res += Integer.parseInt(number);
        }
        System.out.println(res);
    }
}
