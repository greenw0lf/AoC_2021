package Day16;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Day16Part2 {
    public static long literal(Str binary) {
        String number = "";

        String temp = binary.str.substring(1, 5);
        char bool = binary.str.charAt(0);
        number += temp;
        binary.str = binary.str.substring(5);
        while (bool == '1') {
            temp = binary.str.substring(1, 5);
            bool = binary.str.charAt(0);
            number += temp;
            binary.str = binary.str.substring(5);
        }

        return Long.parseLong(number, 2);
    }

    public static long decider(int operator, ArrayList<Long> list) {
        long res;

        switch(operator) {
            case 0:
                res = 0;
                for (long number: list)
                    res += number;
                return res;
            case 1:
                res = 1;
                for (long number: list)
                    res *= number;
                return res;
            case 2:
                res = Integer.MAX_VALUE;
                for (long number: list)
                    res = Math.min(res, number);
                return res;
            case 3:
                res = -1;
                for (long number: list)
                    res = Math.max(res, number);
                return res;
            case 5:
                return list.get(0) > list.get(1) ? 1L : 0L;
            case 6:
                return list.get(0) < list.get(1) ? 1L : 0L;
            case 7:
                return Objects.equals(list.get(0), list.get(1)) ? 1L : 0L;
            default:
                res = 0L;
        }

        return res;
    }

    public static long packet(Str binary) {
        long res = 0L;

        String version = binary.str.substring(0, 3);
        binary.str = binary.str.substring(3);
        String id = binary.str.substring(0, 3);
        binary.str = binary.str.substring(3);

        if (Integer.parseInt(id, 2) == 4)
            res = literal(binary);
        else if (binary.str.charAt(0) == '0') {
            ArrayList<Long> subpackets = new ArrayList<>();
            binary.str = binary.str.substring(1);
            int length = Integer.parseInt(binary.str.substring(0, 15), 2);
            binary.str = binary.str.substring(15);

            int initLen = binary.str.length();

            while (initLen - binary.str.length() < length) {
                subpackets.add(packet(binary));
            }

            res = decider(Integer.parseInt(id, 2), subpackets);
        }
        else {
            ArrayList<Long> subpackets = new ArrayList<>();
            binary.str = binary.str.substring(1);
            int noOfPackets = Integer.parseInt(binary.str.substring(0, 11), 2);
            binary.str = binary.str.substring(11);

            for (int i = 0; i < noOfPackets; i++) {
                subpackets.add(packet(binary));
            }

            res = decider(Integer.parseInt(id, 2), subpackets);
        }

        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(16);

        String hexa = sc.next();
        String binary = "";

        for (int i = 0; i < hexa.length(); i++) {
            StringBuilder digit = new StringBuilder(Integer.toBinaryString(
                    Integer.parseInt("" + hexa.charAt(i), 16)));
            int pain = digit.length();
            for (int j = 0; j < 4 - pain; j++)
                digit.insert(0, "0");
            binary += digit;
        }

        System.out.println(packet(new Str(binary)));
    }
}
